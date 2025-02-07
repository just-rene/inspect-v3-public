package io.ft.stock_market_data_processing.stock_market_data_processing.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class SentimentDayAccumulatedCacheObject {
    @Indexed(unique = true)
    private String value;
}
