package com.easytravel.easytravel.mdbspringboot.controller;

import com.easytravel.easytravel.mdbspringboot.model.TravelRoute;
import com.easytravel.easytravel.mdbspringboot.service.intf.TravelRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/travelRoutes")
public class TravelRouteController {
    @Autowired
    private TravelRouteService service;

    @PostMapping
    public TravelRoute createTravelRoute(@RequestBody TravelRoute route) {
        return service.createTravelRoute(route);
    }

    @GetMapping("/search/{criteria}")
    public List<TravelRoute> searchRoutes(@PathVariable String criteria) {
        return service.searchRoutes(criteria);
    }

    @PostMapping("/plans/{planId}")
    public TravelRoute createTravelRouteWithPlan(@RequestBody TravelRoute route, @PathVariable Integer planId) {
        route.setPlanId(planId);
        return service.createTravelRoute(route);
    }

    @PatchMapping("/routes/{routeId}")
    public TravelRoute updatePriority(@PathVariable Integer routeId, @RequestParam Integer poiId, @RequestParam String priority) {
        return service.updatePriority(routeId, poiId, priority);
    }

    @DeleteMapping("/routes/{routeId}")
    public void deleteTravelRoute(@PathVariable Integer routeId) {
        service.deleteTravelRoute(routeId);
    }

    @PatchMapping("/routes/{routeId}/visibility")
    public TravelRoute toggleVisibility(@PathVariable Integer routeId) {
        return service.toggleVisibility(routeId);
    }
}
