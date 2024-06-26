package com.easytravel.easytravel.mdbspringboot.controller;

import com.easytravel.easytravel.mdbspringboot.model.POI;
import com.easytravel.easytravel.mdbspringboot.service.intf.CustomerService;
import com.easytravel.easytravel.mdbspringboot.service.intf.POIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/pois")
public class FavoritePOIController {

    @Autowired
    private POIService poiService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/favorite/{customerId}")
    public List<POI> getFavoritePOIs(@PathVariable Integer customerId) {
        List<Integer> ids = customerService.getCustomerFavoritePOIList(customerId);
        System.out.println(ids);
        List<POI> pois = new ArrayList<>();

        for (Integer id : ids) {
            POI poi = poiService.getPOIByID(id);
            System.out.println(poi);
            pois.add(poi);
        }

        return pois;
    }

    @GetMapping("/{poiId}")
    public POI getPOI(@PathVariable Integer poiId) {
        return poiService.getPOIByID(poiId);
    }

    @GetMapping("/")
    public List<POI> getAllPOIs() {
        return poiService.getPOIs();
    }

    @PatchMapping("/favorite/{customerId}/{poiId}")
    public String toggleFavoritePOI(@PathVariable Integer customerId, @PathVariable Integer poiId) {
        System.out.println("customer is trying to add favorite " + customerId);
        System.out.println("poi is trying to add favorite " + poiId);
        return poiService.toggleFavoritePOI(customerId, poiId);
    }

    @GetMapping("/name/{name}")
    public POI getPOIByName(@PathVariable String name) {
        return poiService.getPOIByPOIName(name);
    }
}
