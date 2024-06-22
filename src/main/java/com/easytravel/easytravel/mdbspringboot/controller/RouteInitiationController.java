package com.easytravel.easytravel.mdbspringboot.controller;

import com.easytravel.easytravel.mdbspringboot.model.POIArrangement;
import com.easytravel.easytravel.mdbspringboot.model.TravelRoute;
import com.easytravel.easytravel.mdbspringboot.service.intf.TravelRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/travelRoutes")
@CrossOrigin(origins="http://localhost:3000")
public class RouteInitiationController {
    @Autowired
    private TravelRouteService routeService;
    private List<POIArrangement> poiArrangement;

    @PostMapping
    public String addRoute(@RequestBody Map<String, Object> requestData) {

        // what backend receives should be a JSON file
        // Should contain:
        //      agreed: true or false
        //      name: provided
        //      startDate: provided
        //      endDate: provided
        //      poiArrangement: not provided if not agreed, provided if agreed
        //      priority: provided
        //      planId: provided
        //      creatorId: provided

        Boolean agreed = (Boolean) requestData.get("agreed");
        if (!agreed) {
            POIArrangement arrangement = arrange(requestData);
        } else {
            String name = requestData.get("name").toString();
            String startTime = requestData.get("startTime").toString();
            String endTime = requestData.get("endTime").toString();
            String priority = requestData.get("priority").toString();
            Integer planId = Integer.parseInt(requestData.get("planId").toString());
            Integer creatorId = Integer.parseInt(requestData.get("creatorId").toString());

            Integer newId = routeService.getCountTravelRoute() + 1;
            TravelRoute route = new TravelRoute(null, newId, name, startTime, endTime, poiArrangement,
                    priority, planId, creatorId, false);
            routeService.insertTravelRoute(route);

            return "Route added successfully";
        }

        return "Route added successfully";
    }

    public POIArrangement arrange(Map<String, Object> requestData) {

    }

    public String checkOverlap(Map<String, Object> requestData) {
        
    }
}
