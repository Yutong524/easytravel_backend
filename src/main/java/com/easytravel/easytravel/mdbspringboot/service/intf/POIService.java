package com.easytravel.easytravel.mdbspringboot.service.intf;

import com.easytravel.easytravel.mdbspringboot.model.POI;

import java.util.List;

public interface POIService {
    List<POI> getPOIsByKeyword(String keyword);
    List<POI> getPOIsByAddress(String address);
    void removeFavoritePOI(String id);
    POI getPOIByID(Integer id);
}
