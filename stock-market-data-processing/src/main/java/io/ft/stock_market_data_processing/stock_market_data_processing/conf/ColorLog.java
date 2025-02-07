package io.ft.stock_market_data_processing.stock_market_data_processing.conf;

import org.springframework.stereotype.Component;

@Component
public class ColorLog {

    public static String error(String log){
        return "\u001B[31m" + log + "\u001B[0m";
    }

}
