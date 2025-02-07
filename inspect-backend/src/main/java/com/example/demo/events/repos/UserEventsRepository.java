package com.example.demo.events.repos;

import com.example.demo.entities.general.FollowedTopic;
import com.example.demo.events.event.UserEvent;
import org.apache.catalina.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserEventsRepository extends MongoRepository<UserEvent, Long> {

    @Query("{ userId : ?0 }")
    List<UserEvent> getByUserId(String userId);
}
