package com.example.demo.events.event;

import com.example.demo.entities.nlp.computed.SentimentDayAccumulated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class SentimentDayAccumulatedPublishEvent {
    private UUID processId;
    private List<SentimentDayAccumulated> sentimentDayAccumulatedList;
}
