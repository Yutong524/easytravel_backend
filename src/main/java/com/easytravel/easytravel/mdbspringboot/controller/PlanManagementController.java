package com.easytravel.easytravel.mdbspringboot.controller;

import com.easytravel.easytravel.mdbspringboot.model.TravelPlan;
import com.easytravel.easytravel.mdbspringboot.service.intf.TravelPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/travelPlans")
@CrossOrigin(origins="http://localhost:3000")
public class PlanManagementController {

    @Autowired
    private TravelPlanService planService;

    @GetMapping("/{customerId}")
    public List<TravelPlan> getPlan(@PathVariable("customerId") int customerId) {
        System.out.println(customerId);
        return planService.getTravelPlanByAuthor(customerId);
    }

    @DeleteMapping("/{id}")
    public String deletePlan(@PathVariable("id") int id) {
        return planService.deleteTravelPlan(id);
    }

    @GetMapping("/plans/{planId}")
    public TravelPlan getNameofPlan(@PathVariable("planId") int planId) {
        return planService.getNameofTravelPlan(planId);
    }
}
