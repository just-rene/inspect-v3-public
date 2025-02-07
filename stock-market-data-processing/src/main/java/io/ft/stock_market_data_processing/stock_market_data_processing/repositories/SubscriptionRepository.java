package io.ft.stock_market_data_processing.stock_market_data_processing.repositories;

import io.ft.stock_market_data_processing.stock_market_data_processing.entities.Subscription;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends MongoRepository<Subscription, Long> {


}
