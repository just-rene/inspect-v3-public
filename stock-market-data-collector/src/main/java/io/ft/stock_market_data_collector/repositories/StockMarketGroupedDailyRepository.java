package io.ft.stock_market_data_collector.repositories;


import io.ft.stock_market_data_collector.dtos.StockMarketDayDto;
import io.ft.stock_market_data_collector.entities.StockMarketGroupedDaily;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface StockMarketGroupedDailyRepository extends ReactiveMongoRepository<StockMarketGroupedDaily, String> {

    @Aggregation(pipeline = {
            "{$unwind: '$result' }",
            "{ $match: { 'result.ticker': ?0 } }"
    })
    Flux<StockMarketDayDto> findByTicker(String ticker);
}
