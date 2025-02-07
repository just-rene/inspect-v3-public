package com.example.demo.repos;


import com.example.demo.entities.nlp.computed.FollowedTopicSentimentByDay;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface FollowedTopicSentimentByDayRepository extends ReactiveMongoRepository<FollowedTopicSentimentByDay, Long> {


    @Query(value = "{'_id.name': ?0, '_id.entityGroup': ?1}", sort = "{ '_id.date' : 1 }")
    Flux<FollowedTopicSentimentByDay> findByByNameAndEntityGroup(String name, String entityType);




}
