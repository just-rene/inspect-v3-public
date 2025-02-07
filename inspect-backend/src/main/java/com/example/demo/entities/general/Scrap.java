package com.example.demo.entities.general;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
public class Scrap {

    @MongoId
    public ObjectId id;

    @Getter
    @Setter
    public String headline;


    @Getter
    @Setter
    public String content;

}
