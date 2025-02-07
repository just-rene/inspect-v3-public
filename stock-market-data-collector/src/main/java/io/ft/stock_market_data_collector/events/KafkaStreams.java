package io.ft.stock_market_data_collector.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.converters.Auto;
import io.ft.stock_market_data_collector.events.event.UserEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Configuration
public class KafkaStreams {

    @Autowired
    private ObjectMapper objectMapper;
    //consumer
    @Bean("stock-market-subscription")
    public Consumer<String> stockMarketSub() {
        return System.out::println;
    }


    //only for testing
    @Bean("user-events")
    public Supplier<String> UserEventsOut()
    {
        return () -> {
            return null;
        };
    }
}
