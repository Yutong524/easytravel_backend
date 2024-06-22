package com.easytravel.easytravel.mdbspringboot.service.impl;

import com.easytravel.easytravel.mdbspringboot.model.TravelPlan;
import com.easytravel.easytravel.mdbspringboot.repository.TravelPlanRepository;
import com.easytravel.easytravel.mdbspringboot.service.intf.TravelPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return planRepository.deleteTravelPlanByPlanId(id);
    }
}
