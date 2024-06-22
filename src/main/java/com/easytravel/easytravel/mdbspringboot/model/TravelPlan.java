package com.easytravel.easytravel.mdbspringboot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.Data;

@Data
@Document(collection = "travelplans")
public class TravelPlan {
    @Id
    private String id;

    @Field("id")
    private Integer planId;

    @Field("name")
    private String name;

    @Field("description")
    private String description;

    @Field("author")
    private Integer author;

    @Field("tags")
    private String[] tags;

    public TravelPlan(String id, Integer planId, String name, String description, Integer author, String[] tags) {
        super();
        this.id = id;
        this.planId = planId;
        this.name = name;
        this.description = description;
        this.author = author;
        this.tags = tags;
    }
}
