package com.easytravel.easytravel.mdbspringboot.controller;

import com.easytravel.easytravel.mdbspringboot.model.TravelPlan;
import com.easytravel.easytravel.mdbspringboot.service.intf.TravelPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/travelPlans")
@CrossOrigin(origins="http://localhost:3000")
public class PlanInitiationController {
    @Autowired
    private TravelPlanService planService;

    @PostMapping("/")
    public String initiatePlan(@RequestBody String configs) {
        // configs format: name;description;author
        // configs example: "San Fransisco;A plan;1"

        // decipher and translate the configs string
        try {
            String[] parts = configs.split(";");
            if (parts.length != 4) {
                return "Invalid input format.";
            }

            String name = parts[0];
            String des = parts[1];
            Integer author = Integer.parseInt(parts[2]);
            String tags = parts[3];

            String[] tagParts = tags.split(",");

            // need to decide the id of newly created plan
            Integer newId = planService.getCountTravelPlan() + 1;

            TravelPlan plan = new TravelPlan(null, newId, name, des, author, tagParts);

            return planService.insertTravelPlan(plan);
        } catch (Exception e) {
            return "Travel Plan Creation Failed: " + e.getMessage();
        }
    }
}
