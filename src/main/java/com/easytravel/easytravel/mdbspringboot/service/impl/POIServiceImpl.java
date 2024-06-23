package com.easytravel.easytravel.mdbspringboot.service.impl;

import com.easytravel.easytravel.mdbspringboot.model.POI;
import com.easytravel.easytravel.mdbspringboot.repository.POIRepository;
import com.easytravel.easytravel.mdbspringboot.service.intf.POIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class POIServiceImpl implements POIService {
    @Autowired
    private POIRepository poiRepository;

    @Override
    public List<POI> getPOIsByKeyword(String keyword) {
        return poiRepository.findByNameContaining(keyword);
    }

    @Override
    public List<POI> getPOIsByAddress(String address) {
        return poiRepository.findByAddress(address);
    }

    @Override
    public void removeFavoritePOI(String id) {
    }

    @Override
    public void getPOIByID(String id) {

    }
}
