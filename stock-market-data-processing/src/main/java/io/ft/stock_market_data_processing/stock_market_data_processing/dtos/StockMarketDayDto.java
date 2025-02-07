package io.ft.stock_market_data_processing.stock_market_data_processing.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StockMarketDayDto {
    private String date;
    private StockMarketDayDetailsDto result;
}
