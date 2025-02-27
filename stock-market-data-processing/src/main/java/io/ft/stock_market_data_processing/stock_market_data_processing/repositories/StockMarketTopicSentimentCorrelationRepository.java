package io.ft.stock_market_data_processing.stock_market_data_processing.repositories;

import io.ft.stock_market_data_processing.stock_market_data_processing.entities.StockMarketSentimentCorrelation;
import io.ft.stock_market_data_processing.stock_market_data_processing.entities.StockMarketTopicSentimentCorrelation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMarketTopicSentimentCorrelationRepository extends MongoRepository<StockMarketTopicSentimentCorrelation, Long> {



}
