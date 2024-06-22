package com.easytravel.easytravel.mdbspringboot.service.intf;

import com.easytravel.easytravel.mdbspringboot.model.TravelPlan;

import java.util.List;

public interface TravelPlanService {
    List<TravelPlan> getTravelPlanByAuthor(int author);
    String deleteTravelPlan(int id);
}
