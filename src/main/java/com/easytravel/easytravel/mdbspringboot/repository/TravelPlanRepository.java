package com.easytravel.easytravel.mdbspringboot.repository;

import com.easytravel.easytravel.mdbspringboot.model.TravelPlan;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TravelPlanRepository extends MongoRepository<TravelPlan, String> {
    List<TravelPlan> findTravelPlanByAuthor(Integer authorId);
    void deleteTravelPlanByPlanId(Integer planId);
    long count();
    List<TravelPlan> findAllByAuthor(Integer authorId);
}
