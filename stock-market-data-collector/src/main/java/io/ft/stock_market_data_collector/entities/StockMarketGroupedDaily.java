package io.ft.stock_market_data_collector.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.ft.stock_market_data_collector.entities.StockMarketGroupedDaily;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class StockMarketGroupedDaily {

    @Indexed(unique = true)
    private String date;

    @JsonProperty("results")
    private List<StockMarketDaily> result;
}
