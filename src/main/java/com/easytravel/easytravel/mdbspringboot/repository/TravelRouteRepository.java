package com.easytravel.easytravel.mdbspringboot.repository;

import com.easytravel.easytravel.mdbspringboot.model.TravelRoute;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TravelRouteRepository extends MongoRepository<TravelRoute, String>, TravelRouteRepositoryCustom {
    void deleteTravelRouteByRouteId(Integer routeId);
    List<TravelRoute> findTravelRouteByPlanId(Integer planId);
    long count();
    List<TravelRoute> findTravelRouteByCreatorId(Integer creatorId);
}
