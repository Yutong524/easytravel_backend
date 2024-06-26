package com.easytravel.easytravel.mdbspringboot.model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "pois")
public class LandscapePOI extends POI{

    @Field("features")
    private List<String> features;

    @Field("conservation")
    private String conservation;

    @Field("fee")
    private String fee;
}
