package io.ft.stock_market_data_processing.stock_market_data_processing.entities;

import io.ft.stock_market_data_processing.stock_market_data_processing.events.event.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Subscription {

    private SubscriptionDetails _id;

    private String since;

    @Indexed(unique = true)
    private UUID uuid;

}
