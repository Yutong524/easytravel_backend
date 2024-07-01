package com.easytravel.easytravel.mdbspringboot.model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "pois")
public class ShoppingPOI extends POI{

    @Field("services")
    private List<String> services;

    @Field("parking")
    private String parking;

    @Field("wi-fi")
    private String wifi;
}
