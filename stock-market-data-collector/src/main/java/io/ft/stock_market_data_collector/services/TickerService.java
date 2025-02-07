package io.ft.stock_market_data_collector.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TickerService {

    @Autowired
    private ObjectMapper objectMapper;

    //todo: store eventually in db
    public Set<String> getAllTickers() throws IOException {
        var stockTicker =  objectMapper.readTree(new File("src/main/resources/data/all-stock-ticker.json"));
        return stockTicker.findValues("ticker").stream().map(x -> x.asText()).collect(Collectors.toSet());
    }

}
