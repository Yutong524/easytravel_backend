package com.easytravel.easytravel.mdbspringboot.service.impl;

import com.easytravel.easytravel.mdbspringboot.model.TravelPlan;
import com.easytravel.easytravel.mdbspringboot.repository.TravelPlanRepository;
import com.easytravel.easytravel.mdbspringboot.service.intf.TravelPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TravelPlanServiceImpl implements TravelPlanService {
    @Autowired
    private TravelPlanRepository planRepository;

    @Override
    public List<TravelPlan> getTravelPlanByAuthor(int author) {
        return planRepository.findTravelPlanByAuthor(author);
    }

    @Override
    public String deleteTravelPlan(int id) {
        planRepository.deleteTravelPlanByPlanId(id);
        return "Deleted travel plan";
    }

    @Override
    public Integer getCountTravelPlan() {
        return (int) planRepository.count();
    }

    @Override
    public ResponseEntity<String> insertTravelPlan(TravelPlan travelPlan) {
        List<TravelPlan> plans = planRepository.findTravelPlanByAuthor(travelPlan.getAuthor());
        for (TravelPlan plan : plans) {
            if (plan.getName().equals(travelPlan.getName())) {
                throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "405");
            }
        }
        planRepository.save(travelPlan);
        return new ResponseEntity<>("Plan created successfully", HttpStatus.CREATED);
    }
}
