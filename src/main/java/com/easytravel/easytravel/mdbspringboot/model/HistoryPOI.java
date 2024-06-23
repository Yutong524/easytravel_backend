package com.easytravel.easytravel.mdbspringboot.model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Field;

@EqualsAndHashCode(callSuper = true)
@Data
public class HistoryPOI extends POI{

    @Field("architectureStyle")
    private String architectureStyle;

    @Field("establishedYear")
    private Integer establishedYear;

    @Field("access")
    private String access;
}
