package com.easytravel.easytravel.mdbspringboot.service.intf;

import com.easytravel.easytravel.mdbspringboot.model.TravelPlan;
import com.easytravel.easytravel.mdbspringboot.model.TravelRoute;

import java.util.List;

public interface TravelRouteService {
    String changePriorityByRouteId(Integer routeId, String priority);
    String changeVisibilityByRouteId(Integer routeId);
    String deleteRoute(Integer routeId);
    List<TravelRoute> findRouteByPlanId(Integer planId);
    String insertTravelRoute(TravelRoute route);
    Integer getCountTravelRoute();
    List<TravelRoute> findAllTravelRoute(Integer customerId);
    List<TravelRoute> getAllRoutes();
    TravelRoute getTravelRouteById(Integer routeId);
}
