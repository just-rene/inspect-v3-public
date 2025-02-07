package io.ft.stock_market_data_processing.stock_market_data_processing.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SentimentDayAccumulatedKeyDto {
    public String label;
    public String date;
}
