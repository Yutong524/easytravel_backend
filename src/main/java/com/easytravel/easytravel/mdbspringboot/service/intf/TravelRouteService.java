package com.easytravel.easytravel.mdbspringboot.service.intf;

import com.easytravel.easytravel.mdbspringboot.model.TravelRoute;
import java.util.List;

public interface TravelRouteService {
    TravelRoute createTravelRoute(TravelRoute route);

    List<TravelRoute> searchRoutes(String criteria);

    // new added
    TravelRoute findRouteById(Integer routeId);

    TravelRoute updatePriority(Integer routeId, Integer poiId, String priority);

    void deleteTravelRoute(Integer routeId);

    TravelRoute toggleVisibility(Integer routeId);
}