package com.example.demo.rest;


import com.example.demo.entities.general.Job;
import com.example.demo.services.JobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class JobsRest {

    @Autowired
    private JobService jobService;

    @GetMapping(value="/api/jobs/from/{timestampStart}/to/{timestampEnd}")
    public Mono<List<Job>> getJobsFromTo(@PathVariable LocalDateTime timestampStart, @PathVariable LocalDateTime timestampEnd){
        return jobService.getJobsBetween(timestampStart.withHour(0), timestampEnd.withHour(0)).collectList();
    }
}
