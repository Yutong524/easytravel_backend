package com.easytravel.easytravel.mdbspringboot.service.impl;

import com.easytravel.easytravel.mdbspringboot.model.TravelRoute;
import com.easytravel.easytravel.mdbspringboot.repository.TravelRouteRepository;
import com.easytravel.easytravel.mdbspringboot.service.intf.TravelRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelRouteServiceImpl implements TravelRouteService {
    @Autowired
    private TravelRouteRepository routeRepository;

    @Override
    public String changePriorityByRouteId(Integer routeId, String priority) {
        return routeRepository.updatePriorityById(routeId, priority);
    }

    @Override
    public String changeVisibilityByRouteId(Integer routeId) {
        return routeRepository.updateVisibilityById(routeId);
    }

    @Override
    public String deleteRoute(Integer routeId) {
        routeRepository.deleteTravelRouteByRouteId(routeId);
        return "Route Deleted Successfully";
    }

    @Override
    public List<TravelRoute> findRouteByPlanId(Integer planId) {
        return routeRepository.findTravelRouteByPlanId(planId);
    }

    @Override
    public String insertTravelRoute(TravelRoute route) {
        routeRepository.save(route);
        return "Route Inserted Successfully";
    }

    @Override
    public Integer getCountTravelRoute() {
        return (int) routeRepository.count();
    }

}
