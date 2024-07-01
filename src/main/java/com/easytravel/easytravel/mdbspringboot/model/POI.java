package com.easytravel.easytravel.mdbspringboot.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = HistoryPOI.class, name = "history"),
        @JsonSubTypes.Type(value = LandscapePOI.class, name = "recreation"),
        @JsonSubTypes.Type(value = RecreationPOI.class, name = "recreation"),
        @JsonSubTypes.Type(value = ShoppingPOI.class, name = "shopping")
})
@Data
@Document(collection = "pois")
public class POI {
    @Id
    private String id;

    @Field("id")
    private Integer poiId;

    @Field("name")
    private String name;

    @Field("address")
    private String address;

    @Field("category")
    private String category;

    @Field("description")
    private String description;

    @Field("openTime")
    private List<OpenTime> openTime;

    @Field("rating")
    private Double rating;

    @Field("comments")
    private List<Object> comments;

    @Data
    public static class OpenTime {
        @Field("day")
        private String day;

        @Field("startTime")
        private String startTime;

        @Field("endTime")
        private String endTime;
    }

    @Data
    public static class Comment {
        @Field("time")
        private String time;

        @Field("publisher")
        private String publisher;

        @Field("content")
        private String content;

        @Field("rating")
        private double rating;
    }
}
