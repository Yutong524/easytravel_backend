package com.easytravel.easytravel.mdbspringboot.controller;

import com.easytravel.easytravel.mdbspringboot.model.POI;
import com.easytravel.easytravel.mdbspringboot.service.impl.POIServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/easytravel/pois")
public class POISearchController {
    @Autowired
    private POIServiceImpl poiService;

    @GetMapping("/search/{criteria}")
    public List<POI> getPOIs(@PathVariable String criteria) {
        List<POI> poisByKeyWord = poiService.getPOIsByKeyword(criteria);
        List<POI> poisByAddress = poiService.getPOIsByAddress(criteria);

        // Merge the two lists
        List<POI> allPOIs = new ArrayList<>();
        allPOIs.addAll(poisByKeyWord);
        allPOIs.addAll(poisByAddress);

        // Deduplicate the list
        Set<POI> uniquePOIs = Set.copyOf(allPOIs);

        return new ArrayList<>(uniquePOIs);
    }
}
