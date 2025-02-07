package com.example.demo.rest;


import com.example.demo.entities.general.FollowedTopic;
import com.example.demo.entities.general.Topic;
import com.example.demo.entities.nlp.computed.FollowedTopicSentimentByDay;
import com.example.demo.services.FollowedTopicsService;
import com.example.demo.services.TopicService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
public class TopicRest {

    @Autowired
    private FollowedTopicsService followedTopicsService;

    @Autowired
    private TopicService topicService;

    @GetMapping("/api/topic/like/{name}")
    public Flux<Topic> findByName(@PathVariable  String name) {
        if (!name.isBlank()) {
            return topicService.findLike(name);
        }
        return Flux.empty();
    }

    @GetMapping("/api/followedTopic/addTopic/{followedTopicId}/{topicId}")
    public ResponseEntity<String> addTopicToFollowedTopic(@PathVariable long followedTopicId, @PathVariable long topicId) {
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/api/followedTopic/sentimentDayAcc/{followedTopicName}")
    public Flux<FollowedTopicSentimentByDay> sentimentDayAcc(@PathVariable String followedTopicName) {
        var followedTopicMono = followedTopicsService.findByName(followedTopicName);
        return followedTopicsService.getSentimentDayAccumulatedBy(followedTopicMono);
    }

    @GetMapping("/api/followedTopic/getAll")
    public ResponseEntity<Flux<FollowedTopic>> getAllAllFollowedTopics() {
        return new ResponseEntity<>(followedTopicsService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/api/followedTopic/create")
    public ResponseEntity<Mono<FollowedTopic>> create(@RequestBody FollowedTopic followedTopic) throws JsonProcessingException {
        Mono<FollowedTopic> ft = followedTopicsService.create(followedTopic);
        return new ResponseEntity<>(ft, HttpStatus.OK);
    }

}
