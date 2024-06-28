package com.easytravel.easytravel.mdbspringboot.controller;

import com.easytravel.easytravel.mdbspringboot.model.TravelRoute;
import com.easytravel.easytravel.mdbspringboot.service.intf.TravelRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/travelRoutes")
@CrossOrigin(origins="http://localhost:3000")
public class RouteManagementController {

    @Autowired
    private TravelRouteService routeService;

    @PatchMapping("/routes/{routeId}")
    public String changeRoutePriority(@PathVariable("routeId") int routeId, @RequestBody String priority) {
        return routeService.changePriorityByRouteId(routeId, priority);
    }

    @PatchMapping("/routes/{routeId}/visibility")
    public String changeRouteVisibility(@PathVariable("routeId") int routeId) {
        return routeService.changeVisibilityByRouteId(routeId);
    }

    @DeleteMapping("/routes/{routeId}")
    public String deleteRoute(@PathVariable("routeId") int id) {
        return routeService.deleteRoute(id);
    }

    @GetMapping("/plans/{planId}")
    public List<TravelRoute> getRoutes(@PathVariable("planId") int planId) {
        return routeService.findRouteByPlanId(planId);
    }

    @GetMapping("/routes/{customerId}")
    public List<TravelRoute> getRoutesByCustomer(@PathVariable("customerId") int customerId) {
        return routeService.findAllTravelRoute(customerId);
    }
}