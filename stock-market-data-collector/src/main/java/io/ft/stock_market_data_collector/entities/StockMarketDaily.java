package io.ft.stock_market_data_collector.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.core.mapping.Document;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class StockMarketDaily {

    //The exchange symbol that this item is traded under.
    @JsonProperty("T")
    private String ticker;

    //The trading volume of the symbol in the given time period.
    @JsonProperty("v")
    private float tradingVolume;

    //The volume weighted average price.
    @JsonProperty("vw")
    private float volumeWeightedPrice;

    //The open price for the symbol in the given time period.
    @JsonProperty("o")
    private float openPrice;

    //The close price for the symbol in the given time period.
    @JsonProperty("c")
    private float closePrice;

    //The highest price for the symbol in the given time period.
    @JsonProperty("h")
    private float highestPrice;

    //The lowest price for the symbol in the given time period.
    @JsonProperty("l")
    private float lowestPrice;

    //The Unix Msec timestamp for the start of the aggregate window.
    @JsonProperty("t")
    private long timeStamp;

    //The number of transactions in the aggregate window.
    @JsonProperty("n")
    private long transactionNumbers;
}
