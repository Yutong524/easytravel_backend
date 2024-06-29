package com.easytravel.easytravel.mdbspringboot.controller;

import com.easytravel.easytravel.mdbspringboot.model.TravelPlan;
import org.springframework.http.MediaType;
import com.easytravel.easytravel.mdbspringboot.service.intf.TravelPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/travelPlans")
@CrossOrigin(origins="http://localhost:3000")
public class PlanInitiationController {
    @Autowired
    private TravelPlanService planService;

    @PostMapping("/")
    public ResponseEntity<TravelPlan> initiatePlan(@RequestBody String configs) {
        // configs format: name;description;author;tags
        // configs example: "San Fransisco;A plan;1"

        // decipher and translate the configs string
            String[] parts = configs.split(";");

            String name = parts[0];
            String des = parts[1];
            Integer author = Integer.parseInt(parts[2]);
            String tags = parts[3];

            String[] tagParts = tags.split(",");

            // need to decide the id of newly created plan
            Integer newId = planService.getCountTravelPlan() + 1;

            TravelPlan plan = new TravelPlan(null, newId, name, des, author, tagParts);

            return planService.insertTravelPlan(plan);
    }

    @PutMapping(value = "/", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> modifyPlan(@RequestBody String configs) {
        // configs format: name;description;author;tags;planId
        // configs example: "San Francisco;A plan;1;tag1,tag2;5"

        try {
            // Decipher and translate the configs string
            String[] parts = configs.split(";");
            if (parts.length != 5) {
                return ResponseEntity.badRequest().body("Invalid input format");
            }

            String name = parts[0];
            String description = parts[1];
            Integer author = Integer.parseInt(parts[2]);
            String tags = parts[3];
            Integer planId = Integer.parseInt(parts[4]);

            String[] tagList = tags.split(",");

            // Retrieve the existing plan
            TravelPlan existingPlan = planService.getTravelPlanById(planId);
            if (existingPlan == null) {
                return ResponseEntity.notFound().build();
            }

            existingPlan.setName(name);
            existingPlan.setDescription(description);
            existingPlan.setAuthor(author);
            existingPlan.setTags(tagList);

            TravelPlan updatedPlan = planService.saveTravelPlan(existingPlan);

            return ResponseEntity.ok(updatedPlan);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
