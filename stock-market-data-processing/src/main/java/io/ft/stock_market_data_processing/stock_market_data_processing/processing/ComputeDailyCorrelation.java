package io.ft.stock_market_data_processing.stock_market_data_processing.processing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.ft.stock_market_data_processing.stock_market_data_processing.dtos.SentimentDayAccumulatedDto;
import io.ft.stock_market_data_processing.stock_market_data_processing.dtos.StockMarketDayDto;
import io.ft.stock_market_data_processing.stock_market_data_processing.entities.SentimentDayAccumulatedCacheObject;
import io.ft.stock_market_data_processing.stock_market_data_processing.entities.StockMarketSentimentCorrelation;
import io.ft.stock_market_data_processing.stock_market_data_processing.events.event.SentimentDayAccumulatedPublishEvent;
import io.ft.stock_market_data_processing.stock_market_data_processing.repositories.SentimentDayAccumulatedCache;
import io.ft.stock_market_data_processing.stock_market_data_processing.repositories.StockMarketSentimentCorrelationRepository;
import io.ft.stock_market_data_processing.stock_market_data_processing.service.DataRequestService;
import io.ft.stock_market_data_processing.stock_market_data_processing.types.CorrelationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
//computes correlation between stock price and general sentiment
@Component
public class ComputeDailyCorrelation {

    @Autowired
    private DataRequestService dataRequestService;

    @Autowired
    private SentimentDayAccumulatedCache sentimentDayAccumulatedCache;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StockMarketSentimentCorrelationRepository stockMarketSentimentCorrelationDailyRepository;


    public void execute() {

    }

    public void computeForNewTicker(String ticker) throws JsonProcessingException {

        List<StockMarketDayDto> listOfStockMarketDayDtos = dataRequestService.fetchDataForTicker(ticker);
        SentimentDayAccumulatedCacheObject sentimentDayAccumulatedCacheObject = sentimentDayAccumulatedCache.findAll().getLast();
        List<SentimentDayAccumulatedDto> sentimentDayAccumulatedDtoList = objectMapper.readValue(sentimentDayAccumulatedCacheObject.getValue(), SentimentDayAccumulatedPublishEvent.class).getSentimentDayAccumulatedList();


        //1) collect common dates
        ComputeDailyCorrelationData computeDailyCorrelationData = new ComputeDailyCorrelationData();

        for (StockMarketDayDto stockMarketDayDto : listOfStockMarketDayDtos) {

            var date = stockMarketDayDto.getDate();
            // todo use binary search
            List<SentimentDayAccumulatedDto> posNegNeu = new ArrayList<>();
            for (SentimentDayAccumulatedDto sentimentDayAccumulatedDto : sentimentDayAccumulatedDtoList) {
                if (sentimentDayAccumulatedDto._id.getDate().equals(date)) {
                    posNegNeu.add(sentimentDayAccumulatedDto);
                    if (posNegNeu.size() == 3) {
                        //System.err.println("stock: " + date);
                        //System.err.println("sentiment: " + sentimentDayAccumulatedDto._id.getDate());

                        computeDailyCorrelationData.stockMarketData.add(stockMarketDayDto);
                        break;
                    }
                }
            }
            computeDailyCorrelationData.sentimentData.addAll(posNegNeu);
        }


        //2) prepare compute correlation
        List<Float> x = new ArrayList<>();
        List<Float> y_positive = new ArrayList<>();
        List<Float> y_negative = new ArrayList<>();
        List<Float> y_neutral = new ArrayList<>();
        //nt n = 0; //length

        int i = 0;
        for (StockMarketDayDto stockMarketDayDto : computeDailyCorrelationData.stockMarketData) {
            float avgPrice = (stockMarketDayDto.getResult().getHighestPrice() + stockMarketDayDto.getResult().getLowestPrice()) / 2.f;
            x.add(avgPrice);

            for (int j = 0; j < 3; j++) {
                if (computeDailyCorrelationData.sentimentData.get(i + j)._id.label.equals("positive")) {
                    y_positive.add(computeDailyCorrelationData.sentimentData.get(i + j).result);
                }
                if (computeDailyCorrelationData.sentimentData.get(i + j)._id.label.equals("negative")) {
                    y_negative.add(computeDailyCorrelationData.sentimentData.get(i + j).result);
                }
                if (computeDailyCorrelationData.sentimentData.get(i + j)._id.label.equals("neutral")) {
                    y_neutral.add(computeDailyCorrelationData.sentimentData.get(i + j).result);
                }
            }
            i = i + 3;
        }

        StockMarketSentimentCorrelation stockMarketSentimentCorrelation = new StockMarketSentimentCorrelation();
        stockMarketSentimentCorrelation.setTicker(ticker);
        stockMarketSentimentCorrelation.setCorrelationType(CorrelationType.OVERALL_SENTIMENT);
        stockMarketSentimentCorrelation.setCorrelationPositive(computeCorrelation(x, y_positive));
        stockMarketSentimentCorrelation.setCorrelationNegative(computeCorrelation(x, y_negative));
        stockMarketSentimentCorrelation.setCorrelationNeutral(computeCorrelation(x, y_neutral));
        stockMarketSentimentCorrelation.setDataPoints(x.size());

        //remove
        StockMarketSentimentCorrelation deleteThisEntity = stockMarketSentimentCorrelationDailyRepository.findByTicker(ticker).block();
        if (deleteThisEntity != null) {
            stockMarketSentimentCorrelationDailyRepository.delete(deleteThisEntity);
        }

        stockMarketSentimentCorrelationDailyRepository.save(stockMarketSentimentCorrelation);
    }

    private float computeCorrelation(List<Float> x, List<Float> y) {
        //computation pos correlation
        float n = x.size();
        float Sigxy = 0;
        float Sigx = 0;
        float Sigy = 0;

        float Sigx2 = 0;
        float Sigy2 = 0;

        for (int k = 0; k < n; k++) {
            Sigxy += x.get(k) * y.get(k);
            Sigx += x.get(k);
            Sigy += y.get(k);

            Sigx2 += x.get(k) * x.get(k);
            Sigy2 += y.get(k) * y.get(k);
        }

        var res = ((n * Sigxy) - (Sigx * Sigy)) /
                Math.sqrt(((n * Sigx2) - (Sigx * Sigx)) * ((n * Sigy2) - (Sigy * Sigy)));

        return (float) res;
    }

    private class ComputeDailyCorrelationData {
        public List<SentimentDayAccumulatedDto> sentimentData = new ArrayList<>();
        public List<StockMarketDayDto> stockMarketData = new ArrayList<>();
    }

}
