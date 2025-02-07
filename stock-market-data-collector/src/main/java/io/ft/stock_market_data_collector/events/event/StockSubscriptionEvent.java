package io.ft.stock_market_data_collector.events.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class StockSubscriptionEvent {
        private UUID id;
        private UserType userType;
        private String ticker;
        private SubsciptionAction subsciptionAction;
}
