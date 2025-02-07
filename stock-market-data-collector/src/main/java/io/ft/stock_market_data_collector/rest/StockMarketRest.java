package io.ft.stock_market_data_collector.rest;

import io.ft.stock_market_data_collector.dtos.StockMarketDayDto;
import io.ft.stock_market_data_collector.repositories.StockMarketGroupedDailyRepository;
import io.ft.stock_market_data_collector.services.TickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Collections;
import java.util.Set;

@RestController
public class StockMarketRest {

    @Autowired
    private TickerService tickerService;

    @Autowired
    private StockMarketGroupedDailyRepository stockMarketGroupedDailyRepository;

    @GetMapping("/api/stock-market/ticker")
    public HttpEntity<Set<String>> getAllStockMarketTickers() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tickerService.getAllTickers());
        } catch (Exception e) {
            System.err.println("couldn't compute ticker list!");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptySet());
    }


    @GetMapping("/api/get-market-data/ticker/{ticker}")
    public Flux<StockMarketDayDto> getMarketDataForTicker(@PathVariable String ticker) {
        System.err.println("/api/get-market-data/ticker/{ticker}");


        return stockMarketGroupedDailyRepository.findByTicker(ticker);
    }


}
