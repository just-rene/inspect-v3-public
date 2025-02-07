package com.example.demo.scrapper;


import com.example.demo.services.JobService;
import org.apache.hc.core5.http.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component("mongo-scheduler")
public class ScrapperScheduler {

    //todo: in app conf
    @Value("${scrap.on}")
    private boolean SCRAP;


    @Autowired
    private JobService jobsService;

    //pro 1h
    @Scheduled(fixedRate = 3_600_000)
    private void execute() throws NotImplementedException {

        //you need to implement your own crawler
        System.err.println("you need to implement your own web scraper");
        System.err.println("you need to implement your own web scraper");
        System.err.println("you need to implement your own web scraper");
        throw new NotImplementedException();

    }
}
