package io.ft.stock_market_data_processing.stock_market_data_processing.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class StockMarketDayDetailsDto {

    //The exchange symbol that this item is traded under.
    private String ticker;
    //The trading volume of the symbol in the given time period.
    private float tradingVolume;
    //The volume weighted average price.
    private float volumeWeightedPrice;
    //The open price for the symbol in the given time period.
    private float openPrice;
    //The close price for the symbol in the given time period.
    private float closePrice;
    //The highest price for the symbol in the given time period.
    private float highestPrice;
    //The lowest price for the symbol in the given time period.
    private float lowestPrice;
    //The Unix Msec timestamp for the start of the aggregate window.
    private long timeStamp;
    //The number of transactions in the aggregate window.
    private long transactionNumbers;
}
