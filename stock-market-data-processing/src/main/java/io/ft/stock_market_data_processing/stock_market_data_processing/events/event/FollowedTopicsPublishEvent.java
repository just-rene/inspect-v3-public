package io.ft.stock_market_data_processing.stock_market_data_processing.events.event;

import io.ft.stock_market_data_processing.stock_market_data_processing.dtos.TopicDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class FollowedTopicsPublishEvent {

    private UUID processId;
    private List<TopicDto> availableTopics;

}
