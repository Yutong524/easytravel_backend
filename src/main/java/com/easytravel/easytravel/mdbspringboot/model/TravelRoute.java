package com.easytravel.easytravel.mdbspringboot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.Data;

import java.util.List;

@Data
@Document(collection = "travelroutes")
public class TravelRoute {
    @Id
    private String id;

    @Field("routeId")
    private Integer routeId;

    @Field("name")
    private String name;

    @Field("startDate")
    private String startDate;

    @Field("endDate")
    private String endDate;

    @Field("poiArrangement")
    private List<POIArrangement> poiArrangement;

    @Field("priority")
    private String priority;

    @Field("planId")
    private Integer planId;

    @Field("creatorId")
    private Integer creatorId;

    @Field("visibility")
    private Boolean visibility;

    public TravelRoute(String id, String name, String startDate, String endDate, List<POIArrangement> poiArrangement,
                       String priority, Integer planId, Integer creatorId, Boolean visibility) {
        super();
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.poiArrangement = poiArrangement;
        this.priority = priority;
        this.planId = planId;
        this.creatorId = creatorId;
        this.visibility = visibility;
    }
}
