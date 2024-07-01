package com.easytravel.easytravel.mdbspringboot.repository;

import com.easytravel.easytravel.mdbspringboot.model.POI;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.easytravel.easytravel.mdbspringboot.model.POI.Comment;
import java.util.List;
import java.util.Map;

public interface POIRepository extends MongoRepository<POI, String> {
    List<POI> findByNameContaining(String keyword);
    List<POI> findByAddress(String address);
    POI getPOIByPoiId(Integer poiId);
    List<Object> getCommentsByPoiId(Integer poiId);
    POI getPOIByName(String poiName);
    POI findByPoiId(Integer poiId);
}
