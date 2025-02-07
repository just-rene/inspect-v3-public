package com.example.demo.events.rest;

import com.example.demo.conf.ColorLog;
import com.example.demo.dtos.SubscriptionDto;
import com.example.demo.events.event.StockSubscriptionEvent;
import com.example.demo.events.event.SubsciptionAction;
import com.example.demo.events.event.UserType;
import com.example.demo.events.services.SubscriptionEventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@RestController
public class StockMarketRest {

    Logger logger = LoggerFactory.getLogger(StockMarketRest.class);

    @Value("${services.stock-market-data-collector}")
    private String StockMarketDataCollectorService;

    @Value("${services.stock-market-data-processing}")
    private String StockMarketDataProcessingService;

    private final DiscoveryClient discoveryClient;

    @Autowired
    private WebClient.Builder webClient;

    @Autowired
    private ObjectMapper objectMapper;

    public StockMarketRest(DiscoveryClient discoveryClient, RestClient.Builder restClientBuilder) {
        this.discoveryClient = discoveryClient;
    }


    //todo fix
    private boolean isServiceAvailable(String service) {
        List<String> services = discoveryClient.getServices();
        if (!services.contains(service)) {
            logger.error(ColorLog.error("{}  not available"), service);
            return false;
        }
        return true;
    }

    @GetMapping("/api/stock-market/ticker")
    public HttpEntity<Mono<String>> getStockTicker() {
        if (!isServiceAvailable(StockMarketDataCollectorService)) {
            return ResponseEntity.badRequest().body(Mono.just("service not available"));
        }
        return ResponseEntity.ok().body(webClient.build().get().uri("http://" + StockMarketDataCollectorService + "/api/stock-market/ticker").retrieve().bodyToMono(String.class));
    }

    @Autowired
    private SubscriptionEventService subscriptionEventService;

    @PostMapping("/api/stock-market/create-subscription")
    public Mono<ResponseEntity<String>> subscribeToStockMarketTicker(@RequestBody SubscriptionDto body) {

        //trigger event
        StockSubscriptionEvent stockSubscriptionEvent =
                new StockSubscriptionEvent(UUID.randomUUID(), UserType.ADMIN, body.getTicker(), SubsciptionAction.SUBSCRIBE);
        subscriptionEventService.sendStockSubscriptionEvent(stockSubscriptionEvent);

        //response
        ObjectNode rootNode = objectMapper.createObjectNode();
        rootNode.put("name", "successful");
        return Mono.just(ResponseEntity.ok().body(rootNode.toString()));
    }

    @GetMapping("/api/stock-market/ticker-subscriptions")
    public HttpEntity<Flux<String>>  getSubs() {
        if (!isServiceAvailable(StockMarketDataProcessingService)) {
            return ResponseEntity.badRequest().body(Flux.just("service not available"));
        }
        return ResponseEntity.ok().body(webClient.build().get().uri("http://" + StockMarketDataProcessingService + "/api/stock-market/ticker-subscriptions").retrieve().bodyToFlux(String.class));

    }


}
