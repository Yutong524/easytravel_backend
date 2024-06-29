package com.easytravel.easytravel.mdbspringboot.controller;

import com.easytravel.easytravel.mdbspringboot.model.TravelRoute;
import com.easytravel.easytravel.mdbspringboot.service.intf.TravelRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/travelRoutes")
@CrossOrigin(origins="http://localhost:3000")
public class RouteManagementController {

    @Autowired
    private TravelRouteService routeService;

    @PatchMapping("/routes/{routeId}/{oldRouteId}")
    public String changeRoutePriority(@PathVariable("routeId") int routeId,
                                      @PathVariable("oldRouteId") int oldRouteId,
                                      @RequestBody Map<String, String> priorityMap) {
       String result = routeService.changePriorityByRouteId(routeId, priorityMap.get("newPriority"));
       routeService.changePriorityByRouteId(oldRouteId, "none");
       return result;
    }

    @PatchMapping("/routes/{routeId}")
    public String changeRoutePriority(@PathVariable("routeId") int routeId,
                                      @RequestBody Map<String, String> priorityMap) {
        return routeService.changePriorityByRouteId(routeId, priorityMap.get("newPriority"));
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