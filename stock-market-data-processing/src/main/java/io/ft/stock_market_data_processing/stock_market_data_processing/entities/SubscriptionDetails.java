package io.ft.stock_market_data_processing.stock_market_data_processing.entities;

import io.ft.stock_market_data_processing.stock_market_data_processing.events.event.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class SubscriptionDetails {

    private String ticker;
    private UserType userType;
}
