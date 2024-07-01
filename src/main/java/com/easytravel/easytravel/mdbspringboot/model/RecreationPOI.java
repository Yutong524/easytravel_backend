package com.easytravel.easytravel.mdbspringboot.model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "pois")
public class RecreationPOI extends POI{

    @Field("recreationType")
    private String recreationType;

    @Field("petAccess")
    private String petAccess;

    @Field("regulation")
    private String regulation;

    @Field("facilities")
    private List<String> facilities;
}
