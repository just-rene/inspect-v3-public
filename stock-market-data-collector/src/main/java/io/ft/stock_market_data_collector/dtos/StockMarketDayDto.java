package io.ft.stock_market_data_collector.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StockMarketDayDto {
    private String date;
    private Map<String,String> result;
}
