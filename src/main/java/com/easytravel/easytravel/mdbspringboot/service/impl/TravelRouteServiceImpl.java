package com.easytravel.easytravel.mdbspringboot.service.impl;

import com.easytravel.easytravel.mdbspringboot.model.TravelRoute;
import com.easytravel.easytravel.mdbspringboot.repository.TravelRouteRepository;
import com.easytravel.easytravel.mdbspringboot.service.intf.TravelRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TravelRouteServiceImpl implements TravelRouteService {
    private final TravelRouteRepository repository;

    @Autowired
    public TravelRouteServiceImpl(TravelRouteRepository repository) {
        this.repository = repository;
    }

    @Override
    public TravelRoute createTravelRoute(TravelRoute route) {
        return repository.save(route);
    }

    @Override
    public List<TravelRoute> searchRoutes(String criteria) {
        return repository.findAll();
    }

    // new added
    public TravelRoute findRouteById(Integer routeId) {
        Optional<TravelRoute> optionalRoute = repository.findById(routeId);
        return optionalRoute.orElse(null);
    }

    @Override
    public TravelRoute updatePriority(Integer routeId, Integer poiId, String priority) {
        Optional<TravelRoute> optionalRoute = repository.findById(routeId);
        if (optionalRoute.isPresent()) {
            TravelRoute route = optionalRoute.get();
            route.getPoiArrangements().stream()
                    .filter(poi -> poi.getPoiId().equals(poiId))
                    .findFirst()
                    .ifPresent(poi -> poi.setPriority(priority));
            return repository.save(route);
        }
        return null;
    }

    @Override
    public void deleteTravelRoute(Integer routeId) {
        repository.deleteById(routeId);
    }

    @Override
    public TravelRoute toggleVisibility(Integer routeId) {
        Optional<TravelRoute> optionalRoute = repository.findById(routeId);
        if (optionalRoute.isPresent()) {
            TravelRoute route = optionalRoute.get();
            route.setVisibility(!route.getVisibility());
            return repository.save(route);
        }
        return null;
    }
}