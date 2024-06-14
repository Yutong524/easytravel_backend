package com.easytravel.easytravel.mdbspringboot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class Customer {

    @Id
    private Integer id;

    @Field("username")
    private String username;

    @Field("password")
    private String password;

    @Field("favourite_poi")
    private Integer[] favorite_poi;

    @Field("favorite_route")
    private Integer[] favorite_route;

    public Customer(Integer id, String username, String password, Integer[] fpoi, Integer[] froute) {
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
