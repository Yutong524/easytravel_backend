package com.easytravel.easytravel.mdbspringboot.repository;

import com.easytravel.easytravel.mdbspringboot.model.TravelRoute;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelRouteRepository extends MongoRepository<TravelRoute, Integer> {
}