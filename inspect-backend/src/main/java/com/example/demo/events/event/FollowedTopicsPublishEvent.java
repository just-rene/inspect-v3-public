package com.example.demo.events.event;

import com.example.demo.entities.general.FollowedTopic;
import com.example.demo.entities.general.Topic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class FollowedTopicsPublishEvent {

    UUID processId;

    Set<FollowedTopic.Topic> availableTopics;
}
