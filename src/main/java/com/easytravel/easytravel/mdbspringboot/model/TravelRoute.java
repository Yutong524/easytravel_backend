package com.easytravel.easytravel.mdbspringboot.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "travelRoutes")
public class TravelRoute {
    @Id
    private Integer id;
    private String name;
    private String startDate;
    private String endDate;
    private List<POIArrangement> poiArrangements;
    private Integer creatorId;
    private Integer planId;
    private Boolean visibility;

    @Data
    public static class POIArrangement {
        private Integer poiId;
        private String startTime;
        private String endTime;
        private String priority;
    }
}