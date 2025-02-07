package com.example.demo.rest;


import com.example.demo.entities.nlp.computed.NamedEntityRecognitionDayAccumulated;
import com.example.demo.repos.NamedEntityRecognitionDayAccumulatedRepository;
import com.example.demo.services.NamedEntityRecognitionDayAccumulatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.sql.Date;
import java.util.List;

@RestController
public class NerRest {

    @Autowired
    private NamedEntityRecognitionDayAccumulatedService namedEntityRecognitionDayAccumulatedService;

    @GetMapping("/api/ner/all")
    public Flux<NamedEntityRecognitionDayAccumulated> getAll(){
        return namedEntityRecognitionDayAccumulatedService.getAll(120);
    }


    @GetMapping("/api/ner-accumulated/{type}/day/{date}")
    public Flux<NamedEntityRecognitionDayAccumulated> getLocationForDay(@PathVariable  String type, @PathVariable  Date date){
        return namedEntityRecognitionDayAccumulatedService.getByDate(date.toString(), type.toUpperCase());
    }
}
