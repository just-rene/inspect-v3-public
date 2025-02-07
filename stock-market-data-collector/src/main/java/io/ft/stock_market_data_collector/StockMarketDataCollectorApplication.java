package io.ft.stock_market_data_collector;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@EnableMongoRepositories
@EnableScheduling
@SpringBootApplication
public class StockMarketDataCollectorApplication {

	public static void main(String[] args) {
        SpringApplication.run(StockMarketDataCollectorApplication.class, args);
	}
}
