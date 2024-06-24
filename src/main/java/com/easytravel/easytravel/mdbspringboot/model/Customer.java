package com.easytravel.easytravel.mdbspringboot.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.Data;
import org.bson.types.ObjectId;
import java.util.List;


@Data
@Document(collection = "customer")
public class Customer {

    @Id
    private Integer id;

    @Field("username")
    private String username;

    @Field("password")
    private String password;

    @Field("favourite_poi")
    private List<Integer> favorite_poi;

    @Field("favorite_route")
    private List<Integer> favorite_route;

    public Customer(Integer id, String username, String password, List<Integer> fpoi, List<Integer> froute) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.favorite_poi = fpoi;
        this.favorite_route = froute;
    }

    public Customer() {
    }
}
