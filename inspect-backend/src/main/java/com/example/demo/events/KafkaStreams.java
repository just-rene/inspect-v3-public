package com.example.demo.events;

import com.example.demo.entities.general.FollowedTopic;
import com.example.demo.entities.nlp.computed.SentimentDayAccumulated;
import com.example.demo.events.event.UserEvent;
import com.example.demo.events.event.FollowedTopicsPublishEvent;
import com.example.demo.events.event.SentimentDayAccumulatedPublishEvent;
import com.example.demo.events.repos.UserEventsRepository;
import com.example.demo.services.FollowedTopicsService;
import com.example.demo.services.SentimentDayAccumulatedService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Configuration
public class KafkaStreams {

    @Autowired
    private FollowedTopicsService followedTopicsService;


    //todo only publish when something changed
    @Bean("topic-publisher")
    public Supplier<FollowedTopicsPublishEvent> TopicPublisher()
    {
        return () -> {

            return null;
        };
    }

    @Autowired
    private SentimentDayAccumulatedService sentimentDayAccumulatedService;

    //todo only publish when something changed
    @Bean("sentiment-day-accumulated-publisher")
    public Supplier<SentimentDayAccumulatedPublishEvent> SentimentDayAccumulatedPublisher()
    {
        return () -> {
            return  null;
        };
    }



    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserEventsRepository userEventsRepository;

    @Bean("user-events")
    public Consumer<String> UserEventsIn(){
        return x -> {

        };
    }

}
