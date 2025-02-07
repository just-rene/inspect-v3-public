package io.ft.stock_market_data_processing.stock_market_data_processing.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.ft.stock_market_data_processing.stock_market_data_processing.entities.SentimentDayAccumulatedCacheObject;
import io.ft.stock_market_data_processing.stock_market_data_processing.entities.SubscriptionDetails;
import io.ft.stock_market_data_processing.stock_market_data_processing.events.event.FollowedTopicsPublishEvent;
import io.ft.stock_market_data_processing.stock_market_data_processing.events.event.SentimentDayAccumulatedPublishEvent;
import io.ft.stock_market_data_processing.stock_market_data_processing.processing.ComputeDailyCorrelation;
import io.ft.stock_market_data_processing.stock_market_data_processing.repositories.SentimentDayAccumulatedCache;
import io.ft.stock_market_data_processing.stock_market_data_processing.repositories.StockMarketSentimentCorrelationRepository;
import io.ft.stock_market_data_processing.stock_market_data_processing.repositories.SubscriptionRepository;
import io.ft.stock_market_data_processing.stock_market_data_processing.entities.Subscription;
import io.ft.stock_market_data_processing.stock_market_data_processing.events.event.StockSubscriptionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.function.Consumer;

@Configuration
public class KafkaStreams {


    @Autowired
    private StockMarketSentimentCorrelationRepository stockMarketSentimentCorrelationDailyRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ComputeDailyCorrelation computeDailyCorrelation;


    @Autowired
    private SentimentDayAccumulatedCache sentimentDayAccumulatedCache;

    //when someone subscribes to a new ticker -> triggers correlation computation
    @Bean("stock-market-subscription")
    public Consumer<String> stockMarketSub() {
        return x -> {

        };
    }

    //upcoming feature will be later computed with stock data
    @Bean("topic-publisher")
    public Consumer<String> topicPublisherSub(){
        return x -> {

        };
    }

    @Bean("sentiment-day-accumulated-publisher")
    public Consumer<String> sentimentPublisherSub(){
        return x -> {

        };
    }
}
