package io.ft.stock_market_data_processing.stock_market_data_processing.rest;

import io.ft.stock_market_data_processing.stock_market_data_processing.entities.StockMarketSentimentCorrelation;
import io.ft.stock_market_data_processing.stock_market_data_processing.repositories.StockMarketSentimentCorrelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class SubscriptionRest {

    @Autowired
    private StockMarketSentimentCorrelationRepository stockMarketSentimentCorrelationRepository;

    @GetMapping("/api/stock-market/ticker-subscriptions")
    public Flux<StockMarketSentimentCorrelation> getSubs() {
        return stockMarketSentimentCorrelationRepository.findAll();
    }
}
