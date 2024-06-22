package com.easytravel.easytravel.mdbspringboot.model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class RecreationPOI extends POI{

    @Field("recreationType")
    private String recreationType;

    @Field("petAccess")
    private Boolean petAccess;

    @Field("regulation")
    private String regulation;

    @Field("facilities")
    private List<String> facilities;
}
