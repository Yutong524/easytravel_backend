package com.easytravel.easytravel.mdbspringboot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.Data;

@Data
public class POIArrangement {

    @Field("poiId")
    private Integer poiId;

    @Field("startTime")
    private String startTime;

    @Field("endTime")
    private String endTime;

    public POIArrangement(Integer poiId, String startTime, String endTime) {
        super();
        this.poiId = poiId;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
