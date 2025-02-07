package com.example.demo.repos;


import com.example.demo.entities.general.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface TopicRepository extends ReactiveMongoRepository<Topic, String> {

    @Query("{'name': {$regex : ?0, $options: 'i'}})")
    Flux<Topic> findLike(String name);
}
