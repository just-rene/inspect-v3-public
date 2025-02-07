package io.ft.stock_market_data_collector.conf;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.ft.stock_market_data_collector.scheduler.StockMarketGroupedDailyScheduler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class InitBean implements InitializingBean {


    @Autowired
    private StockMarketGroupedDailyScheduler stockMarketGroupedDailyScheduler;

    @Value("${config.fetch_data_for_last_n_days}")
    private int daysToFetch;

    @Value("${config.fetch_data_on_startup.active}")
    private boolean fetchingActive;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (fetchingActive){
            System.err.println("fetching last" + daysToFetch + "days!");
            stockMarketGroupedDailyScheduler.computeLastNDays(daysToFetch);
        }
    }
}
