package com.example.demo.sse;

import com.example.demo.events.event.UserEvent;
import com.example.demo.events.repos.UserEventsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.beans.Transient;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Function;

//upcoming feature
@RestController
public class StockSse {


    @Autowired
    private UserEventsRepository userEventsRepository;

    @Autowired
    private ObjectMapper objectMapper;




    @GetMapping(path = "/ticker-stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamFlux() throws Exception {
        //upcoming feature
        return Flux.empty();
    }
}
