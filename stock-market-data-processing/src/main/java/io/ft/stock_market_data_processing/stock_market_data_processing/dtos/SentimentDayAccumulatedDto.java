package io.ft.stock_market_data_processing.stock_market_data_processing.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SentimentDayAccumulatedDto {
    public SentimentDayAccumulatedKeyDto _id;
    public float result;
}
