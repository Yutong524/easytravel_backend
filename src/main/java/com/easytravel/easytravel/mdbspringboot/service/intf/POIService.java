package com.easytravel.easytravel.mdbspringboot.service.intf;

import com.easytravel.easytravel.mdbspringboot.model.POI;
import com.easytravel.easytravel.mdbspringboot.model.POI.Comment;
import java.util.List;
import java.util.Map;

public interface POIService {
    List<POI> getPOIsByKeyword(String keyword);
    List<POI> getPOIsByAddress(String address);
    void removeFavoritePOI(String id);
    POI getPOIByID(Integer id);
    public String toggleFavoritePOI(Integer customerId, Integer poiId);

    // added by Danny
    List<Comment> getPOICommentsByPoiId(Integer poiId);
    // added by Danny
    public Double insertPOICommentByPoiId(Integer poiId, Map<String, Object> comment);

    List<POI> getPOIs();
    POI getPOIByPOIName(String poiName);
}
