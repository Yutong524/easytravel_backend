package com.easytravel.easytravel.mdbspringboot.repository;

import com.easytravel.easytravel.mdbspringboot.model.POI;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface POIRepository extends MongoRepository<POI, Integer> {
    List<POI> findByNameContaining(String keyword);
    List<POI> findByAddress(String address);
}
