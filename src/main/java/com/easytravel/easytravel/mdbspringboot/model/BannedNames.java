package com.easytravel.easytravel.mdbspringboot.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
@Document(collection = "bannednames")
@Data
public class BannedNames {

    @Id
    private String id;

    @Field("names")
    private String[] names;
}
