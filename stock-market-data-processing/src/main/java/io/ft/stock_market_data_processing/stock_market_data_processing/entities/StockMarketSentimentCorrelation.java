package io.ft.stock_market_data_processing.stock_market_data_processing.entities;

import io.ft.stock_market_data_processing.stock_market_data_processing.dtos.TopicDto;
import io.ft.stock_market_data_processing.stock_market_data_processing.types.CorrelationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class StockMarketSentimentCorrelation {
    private ObjectId _id;
    @Indexed(unique = true)
    private String ticker;
    private CorrelationType correlationType;
    private float correlationPositive;
    private float correlationNegative;
    private float correlationNeutral;
    private int dataPoints = 0;
}
