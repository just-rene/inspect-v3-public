package com.example.demo.events.services;

import com.example.demo.events.event.StockSubscriptionEvent;
import com.example.demo.events.event.SubsciptionAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionEventService {

    @Autowired
    private StreamBridge streamBridge;

    public void sendStockSubscriptionEvent(StockSubscriptionEvent stockSubscriptionEvent){
        streamBridge.send("stock-market-subscription-in-0", stockSubscriptionEvent);
    }
}
