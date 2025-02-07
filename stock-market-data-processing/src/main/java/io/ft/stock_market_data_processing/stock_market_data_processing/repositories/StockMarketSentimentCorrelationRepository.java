package io.ft.stock_market_data_processing.stock_market_data_processing.repositories;

import io.ft.stock_market_data_processing.stock_market_data_processing.entities.StockMarketSentimentCorrelation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface StockMarketSentimentCorrelationRepository extends ReactiveMongoRepository<StockMarketSentimentCorrelation, Long> {


    @Query("{ ticker : ?0 }")
    Mono<StockMarketSentimentCorrelation> findByTicker(String ticker);



}
