package com.example.demo.services;

import com.example.demo.entities.nlp.computed.SentimentDayAccumulated;
import com.example.demo.repos.SentimentDayAccumulatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class SentimentDayAccumulatedService {

    @Autowired
    private SentimentDayAccumulatorRepository sentimentDayAccumulatedRepository;

    public void save(SentimentDayAccumulated s){
        sentimentDayAccumulatedRepository.save(s);
    }


    public Flux<SentimentDayAccumulated> getAll(){
        return sentimentDayAccumulatedRepository.findAll();
    }

}
