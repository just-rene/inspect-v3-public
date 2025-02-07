package io.ft.stock_market_data_processing.stock_market_data_processing.conf;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;

public class InitBean implements InitializingBean {



    @Override
    public void afterPropertiesSet() throws Exception {
        //init
    }
}
