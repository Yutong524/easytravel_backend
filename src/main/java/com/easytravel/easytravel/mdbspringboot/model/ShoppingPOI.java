package com.easytravel.easytravel.mdbspringboot.model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ShoppingPOI extends POI{

    @Field("services")
    private List<String> services;

    @Field("parking")
    private Boolean parking;

    @Field("wi-fi")
    private Boolean wifi;
}
