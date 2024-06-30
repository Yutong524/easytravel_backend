package com.easytravel.easytravel.mdbspringboot.controller;

import com.easytravel.easytravel.mdbspringboot.model.POI;
import com.easytravel.easytravel.mdbspringboot.model.POIArrangement;
import com.easytravel.easytravel.mdbspringboot.model.TravelRoute;
import com.easytravel.easytravel.mdbspringboot.service.intf.TravelRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/travelRoutes")
@CrossOrigin(origins="http://localhost:3000")
public class RouteInitiationController {
    @Autowired
    private TravelRouteService routeService;
    private List<POIArrangement> poiArrangement;

    @PostMapping("/outside")
    public String addRouteOutside(@RequestBody Map<String, Object> requestData) {
        System.out.println(requestData.get("selectedPlan"));
        Integer newId = routeService.getCountTravelRoute() + 1;
        String name = requestData.get("routeName").toString();
        String startDate = requestData.get("startDate").toString();
        String endDate = requestData.get("endDate").toString();

        List<POIArrangement> arrangements = new ArrayList<>();
        List<Map<String, Object>> schedules = (List<Map<String, Object>>) requestData.get("poiSchedules");
        for (Map<String, Object> schedule : schedules) {
            LocalDate localDate = LocalDate.parse(schedule.get("date").toString());
            LocalTime startTime = LocalTime.
                    parse(schedule.get("startTime").toString(), DateTimeFormatter.ofPattern("HH:mm:ss"));
            LocalTime endTime = LocalTime.
                    parse(schedule.get("endTime").toString(), DateTimeFormatter.ofPattern("HH:mm:ss"));
            String formattedStartTime = startTime.format(DateTimeFormatter.ofPattern("HH-mm-ss"));
            String formattedEndTime = endTime.format(DateTimeFormatter.ofPattern("HH-mm-ss"));
            String start = localDate + " " + formattedStartTime;
            String end = localDate + " " + formattedEndTime;
            POIArrangement arrangement =
                    new POIArrangement(Integer.parseInt(schedule.get("poiId").toString()), start, end);
            arrangements.add(arrangement);
        }

        String priority = requestData.get("priority").toString();
        Integer planId = 0;
        if (requestData.get("selectedPlan") != null) {
            Map<String, Object> selectedPlan = (Map<String, Object>) requestData.get("selectedPlan");
            planId = Integer.parseInt(selectedPlan.get("planId").toString());
        }
        Integer customerId = Integer.parseInt(requestData.get("customerId").toString());

        TravelRoute newRoute = new TravelRoute(null, newId, name, startDate, endDate, arrangements, priority, planId, customerId, false);
        return routeService.insertTravelRoute(newRoute);
    }

    @PostMapping("/inside")
    public String addRouteInside(@RequestBody Map<String, Object> requestData) {
        System.out.println(requestData.get("plan"));
        Integer newId = routeService.getCountTravelRoute() + 1;
        String name = requestData.get("routeName").toString();
        String startDate = requestData.get("startDate").toString();
        String endDate = requestData.get("endDate").toString();

        List<POIArrangement> arrangements = new ArrayList<>();
        List<Map<String, Object>> schedules = (List<Map<String, Object>>) requestData.get("poiSchedules");
        for (Map<String, Object> schedule : schedules) {
            LocalDate localDate = LocalDate.parse(schedule.get("date").toString());
            LocalTime startTime = LocalTime.
                    parse(schedule.get("startTime").toString(), DateTimeFormatter.ofPattern("HH:mm:ss"));
            LocalTime endTime = LocalTime.
                    parse(schedule.get("endTime").toString(), DateTimeFormatter.ofPattern("HH:mm:ss"));
            String formattedStartTime = startTime.format(DateTimeFormatter.ofPattern("HH-mm-ss"));
            String formattedEndTime = endTime.format(DateTimeFormatter.ofPattern("HH-mm-ss"));
            String start = localDate + " " + formattedStartTime;
            String end = localDate + " " + formattedEndTime;
            POIArrangement arrangement =
                    new POIArrangement(Integer.parseInt(schedule.get("poiId").toString()), start, end);
            arrangements.add(arrangement);
        }

        String priority = requestData.get("priority").toString();
        Map<String, Object> selectedPlan = (Map<String, Object>) requestData.get("plan");
        Integer planId = Integer.parseInt(selectedPlan.get("planId").toString());
        Integer customerId = Integer.parseInt(selectedPlan.get("customerId").toString());

        TravelRoute newRoute = new TravelRoute(null, newId, name, startDate, endDate, arrangements, priority, planId, customerId, false);
        return routeService.insertTravelRoute(newRoute);
    }
}
