package com.example.demo.nlp;


import com.example.demo.entities.general.Job;
import com.example.demo.entities.nlp.*;
import com.example.demo.services.JobService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
public class NlpScheduler {

    //todo: app conf
    private final boolean emoOn = true;

    @Autowired
    private EmotionMultilabelAnalysis emotionMultilabelAnalysis;

    @Autowired
    private SentimentAnalysis sentimentAnalysis;

    @Autowired
    private NamedEntityRecognitionAnalysis namedEntityRecognitionAnalysis;

    @Autowired
    private JobService jobService;

    @Autowired
    private ObjectMapper objectMapper;


    @Scheduled(fixedRate = 3_600_000 ) //
    private void execute() throws JsonProcessingException {

        //EMOTION MULTILABEL
        jobService.getJobsWithoutNlpAnalysis(NlpTask.EMOTION_MULTILABEL).subscribe(
                this::computeEmotionMultilabel,
                Throwable::printStackTrace,
                System.err::println
        );


        //SENTIMENT
        jobService.getJobsWithoutNlpAnalysis(NlpTask.SENTIMENT).subscribe(
                this::computeSentiment,
                Throwable::printStackTrace,
                System.err::println
        );


        //NER
        jobService.getJobsWithoutNlpAnalysis(NlpTask.NAMED_ENTITY_RECOGNITION).subscribe(
                this::computeNer,
                Throwable::printStackTrace,
                System.err::println
        );
    }


    private void computeEmotionMultilabel(Job notAnalysedJobEmotionMultilabel) {

        String jsonResult = emotionMultilabelAnalysis.getEmotionAnalysis(notAnalysedJobEmotionMultilabel.getScrap().getHeadline());
        EmotionMultilabel emotionMultilabel = new EmotionMultilabel();

        List<EmotionMultilabelPair> listOfResults = null;
        try {
            listOfResults = objectMapper.readValue("[" + jsonResult.substring(2, jsonResult.length() - 2) + "]", new TypeReference<List<EmotionMultilabelPair>>() {
            });
        } catch (JsonProcessingException e) {
            Logger.getAnonymousLogger("NlpScheduler.computeEmotionMultilabel failed! JSON ObjectMapper error!");
        }

        emotionMultilabel.setResult(listOfResults);
        notAnalysedJobEmotionMultilabel.setEmotionMultilabel(emotionMultilabel);
        jobService.save(notAnalysedJobEmotionMultilabel);

    }

    private void computeSentiment(Job notAnalysedJobsSentiment) {

        String jsonResult = sentimentAnalysis.getAnalysis(notAnalysedJobsSentiment.getScrap().getHeadline());
        Sentiment sentiment = new Sentiment();
        List<SentimentPair> listOfResults = null;

        try {
            listOfResults = objectMapper.readValue("[" + jsonResult.substring(2, jsonResult.length() - 2) + "]", new TypeReference<List<SentimentPair>>() {
            });
        } catch (JsonProcessingException e) {
            Logger.getAnonymousLogger("NlpScheduler.computeSentiment failed! JSON ObjectMapper error!");
        }
        sentiment.setResult(listOfResults);
        notAnalysedJobsSentiment.setSentiment(sentiment);
        jobService.save(notAnalysedJobsSentiment);

    }

    private void computeNer(Job notAnalysedJobsNer) {


        String jsonResult = namedEntityRecognitionAnalysis.getAnalysis(notAnalysedJobsNer.getScrap().getContent());
        NamedEntityRecognition ner = new NamedEntityRecognition();
        List<NamedEntityRecognitionPair> listOfResults = null;

        try {
            listOfResults = objectMapper.readValue(jsonResult, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            Logger.getAnonymousLogger("NlpScheduler.computeNer failed! JSON ObjectMapper error!");
        }
        ner.setResult(listOfResults);
        notAnalysedJobsNer.setNamedEntityRecognition(ner);
        jobService.save(notAnalysedJobsNer);

    }

}
