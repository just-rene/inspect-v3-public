package io.ft.stock_market_data_collector.scheduler;

import io.ft.stock_market_data_collector.entities.StockMarketGroupedDaily;
import io.ft.stock_market_data_collector.repositories.StockMarketGroupedDailyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;

@Component
public class StockMarketGroupedDailyScheduler {

    @Autowired
    private WebClient webClientForGroupedDaily;

    @Value("${polygon.api_key}")
    public String apiKey;

    // private String date = "2023-01-11";

    @Autowired
    private StockMarketGroupedDailyRepository stockMarketGroupedDailyRepository;

    //UTC +0000
    //<second> <minute> <hour> <day of month> <month> <day of week>
    @Scheduled(cron = "0 0 4 * * *", zone = "UTC")
    //@Scheduled(fixedRate = 60000)
    public void execute() {
        LocalDate date = LocalDate.now().minusDays(1); //yesterday
        saveForDate(date);
    }

    public void saveForDate(LocalDate date) {
        var request = webClientForGroupedDaily.get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("v2/aggs/grouped/locale/us/market/stocks")
                        .pathSegment(date.toString())
                        .queryParam("adjusted", true)
                        .queryParam("apiKey", apiKey)
                        .build());

        var result = request.retrieve().toEntity(StockMarketGroupedDaily.class).block();
        if (result != null && result.hasBody()) {
            var nonNullResult = result.getBody();
            if (nonNullResult.getResult() != null){
                nonNullResult.setDate(date.toString());
                stockMarketGroupedDailyRepository.save(nonNullResult);
                System.out.println("stockMarketGroupedDaily saved for date: " + date);
            }

        } else {
            System.err.println("stockMarketGroupedDaily were not saved, for date: " + date);
        }
    }


    public void computeLastNDays(int n) throws InterruptedException {

        for (int minusDays = 1; minusDays < n; minusDays++) {
            //can only perform 5 api calls per minute (rate limited)
            if (minusDays % 5 == 0) {
                Thread.sleep(60000);
            }
            LocalDate date = LocalDate.now().minusDays(minusDays);
            try {
                saveForDate(date);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
