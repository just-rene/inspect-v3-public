package io.ft.stock_market_data_processing.stock_market_data_processing.service;

import io.ft.stock_market_data_processing.stock_market_data_processing.conf.ColorLog;
import io.ft.stock_market_data_processing.stock_market_data_processing.dtos.StockMarketDayDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

@Service
public class DataRequestService {

    @Value("${services.stock-market-data-collector}")
    private String serviceName;

    @Autowired
    private WebClient.Builder webClient;

    Logger logger = LoggerFactory.getLogger(DataRequestService.class);

    private final DiscoveryClient discoveryClient;

    public DataRequestService(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }


    private boolean isServiceAvailable() {
        List<String> services = discoveryClient.getServices();
        if (!services.contains(serviceName)) {
            logger.error(ColorLog.error("{}  not available"), serviceName);
            return false;
        }
        return true;
    }

    public List<StockMarketDayDto> fetchDataForTicker(String ticker) {
        if (isServiceAvailable()) {
            List<StockMarketDayDto> marketDataPoints = ResponseEntity.ok().body(webClient.build().get().uri("http://" + serviceName + "/api/get-market-data/ticker/" + ticker)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<StockMarketDayDto>>() {
                    })).getBody().block(Duration.ofSeconds(20));
            return marketDataPoints;
        }
        return List.of();
    }


}
