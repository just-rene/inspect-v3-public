package io.ft.stock_market_data_collector.scheduler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${polygon.base_url}")
    private String baseUrl;

    @Bean("polygon")
    public WebClient webClient(){
        return WebClient.builder().codecs(configure -> configure
                        .defaultCodecs()
                        .maxInMemorySize(16 * 1024 * 1024))
                .baseUrl(baseUrl)
                .build();
    }

}
