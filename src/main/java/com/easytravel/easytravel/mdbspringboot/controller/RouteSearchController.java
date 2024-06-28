package com.easytravel.easytravel.mdbspringboot.controller;

import com.easytravel.easytravel.mdbspringboot.model.Customer;
import com.easytravel.easytravel.mdbspringboot.model.POIArrangement;
import com.easytravel.easytravel.mdbspringboot.model.TravelPlan;
import com.easytravel.easytravel.mdbspringboot.model.TravelRoute;
import com.easytravel.easytravel.mdbspringboot.service.intf.CustomerService;
import com.easytravel.easytravel.mdbspringboot.service.intf.TravelRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/travelRoutes")
@CrossOrigin(origins="http://localhost:3000")
public class RouteSearchController {

    @Autowired
    private TravelRouteService routeService;

    @Autowired
    private CustomerService custService;

    @PostMapping("/search")
    public List<TravelRoute> searchRoute(@RequestBody String configs) {
        // configs format: duration;poiId1,poiId2,...
        if (configs.startsWith("\"") && configs.endsWith("\"")) {
            configs = configs.substring(1, configs.length() - 1);
        }
        // decipher and translate the configs string
        String[] parts = configs.split(";");

        int duration = Integer.parseInt(parts[0]);
        String ids = parts[1];
        Set<Integer> idSet = Arrays.stream(ids.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());

        List<TravelRoute> allRoutes = routeService.getAllRoutes();
        List<TravelRoute> results = new ArrayList<>();

        for (TravelRoute route : allRoutes) {
            int saved_duration = Math.toIntExact(calculateDuration(route.getStartDate(), route.getEndDate()));
            if (saved_duration != duration) {
                continue;
            }

            Set<Integer> routePoiIds = route.getPoiArrangement().stream()
                    .map(POIArrangement::getPoiId)
                    .collect(Collectors.toSet());

            if (routePoiIds.containsAll(idSet)) {
                results.add(route);
            }
        }

        return results;
    }

    private static long calculateDuration(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        return ChronoUnit.DAYS.between(start, end);
    }

    @PatchMapping("/favorite/{customerId}/{routeId}")
    public void toggleFavorite(@PathVariable Integer customerId, @PathVariable Integer routeId) {
        List<Integer> favorites = custService.getFavoriteRoute(customerId);
        if (favorites.contains(routeId)) {
            favorites.remove(routeId);
        } else {
            favorites.add(routeId);
        }

        custService.modifyFavoriteRoute(customerId, favorites);
    }

    @GetMapping("/favorite/{customerId}")
    public List<Integer> getFavoriteRoute(@PathVariable Integer customerId) {
        return custService.getFavoriteRoute(customerId);
    }

    @GetMapping("/routes/id/{routeId}")
    public TravelRoute getRouteById(@PathVariable("routeId") int routeId) {
        return routeService.getTravelRouteById(routeId);
    }

}
