package com.example.demo.repos;


import com.example.demo.entities.general.FollowedTopic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface FollowedTopicRepository extends ReactiveMongoRepository<FollowedTopic, Long> {

    @Query("{ name : ?0 }")
    Mono<FollowedTopic> findByName(String name);
}

