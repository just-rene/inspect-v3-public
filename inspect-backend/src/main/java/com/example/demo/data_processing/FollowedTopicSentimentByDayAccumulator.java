package com.example.demo.data_processing;


import com.example.demo.repos.FollowedTopicRepository;
import com.example.demo.repos.FollowedTopicSentimentByDayRepository;
import com.example.demo.repos.JobRepository;
import com.example.demo.repos.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FollowedTopicSentimentByDayAccumulator implements IAccumulator {


    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private FollowedTopicRepository followedTopicRepository;

    @Autowired
    private FollowedTopicSentimentByDayRepository followedTopicSentimentByDayRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Scheduled(fixedRate = 24 * 3_600_000)
    @Override
    public void execute() {
        //this is GMT+2 Timezone, Mongo will -2 on this Datetime
        int computeNLastDays = 1;
        for (int day = 0; day < computeNLastDays; day++) {
            LocalDateTime start = LocalDateTime.now().withHour(2).withMinute(0).withSecond(0).withNano(0).minusDays(day);//change back to 1
            LocalDateTime end = start.plusDays(1);

            var followedTopics = followedTopicRepository.findAll().collectList().block();

            assert followedTopics != null;
            for (var followedTopic : followedTopics) {
                for (var topic : followedTopic.getTopics()) {

                    jobRepository.triggerFollowedTopicSentimentByDay(start, end, topic.getName(), topic.getEntityGroup()).block();

                }
            }


        }
    }
}
