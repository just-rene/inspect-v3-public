package com.example.demo.services;


import com.example.demo.entities.general.FollowedTopic;
import com.example.demo.entities.nlp.computed.FollowedTopicSentimentByDay;
import com.example.demo.repos.FollowedTopicRepository;
import com.example.demo.repos.FollowedTopicSentimentByDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowedTopicsService{

    @Autowired
    private FollowedTopicRepository followedTopicsRepository;

    @Autowired
    private FollowedTopicSentimentByDayRepository followedTopicSentimentByDayRepository;

    public Mono<FollowedTopic> create(FollowedTopic followedTopic) {

        if (followedTopic.getTopics().isEmpty() || followedTopic.getName().isBlank()) {
            throw new IllegalArgumentException("At least 1 Topic is needed! Name must not be blank");
        }
        return followedTopicsRepository.save(followedTopic);
    }

    public Flux<FollowedTopic> findAll() {
        return followedTopicsRepository.findAll();
    }

    public Flux<FollowedTopicSentimentByDay> getSentimentDayAccumulatedBy(Mono<FollowedTopic> followedTopic) {


         var result  = followedTopic.map(ft -> {
            var x = ft.getTopics();
            var namesList = x.stream().map(FollowedTopic.Topic::getName).toList();
            var entityGroupList = x.stream().map(FollowedTopic.Topic::getEntityGroup).toList();

            List<Flux<FollowedTopicSentimentByDay>> res = new ArrayList<>();
            for (int i = 0; i < x.size(); i++) {
                res.add(followedTopicSentimentByDayRepository.findByByNameAndEntityGroup(namesList.get(i), entityGroupList.get(i)));

            }

            return Flux.merge(res);
        });


        

        return Flux.merge(result);
    }



    public Mono<FollowedTopic> findByName(String name) {
        return followedTopicsRepository.findByName(name);
    }
}
