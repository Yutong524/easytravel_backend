package com.easytravel.easytravel.mdbspringboot.controller;

import com.easytravel.easytravel.mdbspringboot.model.POI;
import com.easytravel.easytravel.mdbspringboot.model.POIArrangement;
import com.easytravel.easytravel.mdbspringboot.model.TravelRoute;
import com.easytravel.easytravel.mdbspringboot.service.intf.TravelRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @PostMapping
    public Object addRoute(@RequestBody Map<String, Object> requestData) {

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
            List<POIArrangement> currentArrangement = (List<POIArrangement>) requestData.get("poiArrangement");
            String startTime = requestData.get("startTime").toString();
            String endTime = requestData.get("endTime").toString();
            List<String> dateList = generateDateList(startTime, endTime);
            if (currentArrangement != null) {
                this.poiArrangement = currentArrangement;
                return checkApplicable(currentArrangement, dateList);
            } else {
                POIArrangement arrangement = arrange(requestData);
                return "arrangement";
            }
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
    }

    public String checkApplicable(List<POIArrangement> currentArrangement, List<String> dateList) {
        for (int i = 0; i < currentArrangement.size(); i++) {
            for (int j = i + 1; j < currentArrangement.size(); j++) {
                if (isOverlap(currentArrangement.get(i), currentArrangement.get(j))) {
                    return "Not applicable";
                }
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
        for (int i = 0; i < dateList.size(); i++) {
            for (int j = 0; j < currentArrangement.size(); j++) {
                if (LocalDate.parse(currentArrangement.get(j).getStartTime(), formatter).isEqual(LocalDate.parse(dateList.get(i)))) {
                    break;
                }
            }
            return "Day free";
        }
        return "Applicable";
    }

    public static List<String> generateDateList(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        List<String> dateList = new ArrayList<>();
        LocalDate currentDate = start;

        while (!currentDate.isAfter(end)) {
            dateList.add(currentDate.format(formatter));
            currentDate = currentDate.plusDays(1);
        }
        return dateList;
    }

    private static boolean isOverlap(POIArrangement a, POIArrangement b) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime aStart = LocalDateTime.parse(a.getStartTime(), formatter);
        LocalDateTime bStart = LocalDateTime.parse(b.getStartTime(), formatter);
        LocalDateTime aEnd = LocalDateTime.parse(a.getEndTime(), formatter);
        LocalDateTime bEnd = LocalDateTime.parse(b.getEndTime(), formatter);
        return !(aEnd.isBefore(bStart) || bEnd.isBefore(aStart));
    }


    public POIArrangement arrange(Map<String, Object> requestData) {
        return (POIArrangement) requestData.get("poiArrangement");
    }
}
