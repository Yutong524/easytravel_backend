package com.easytravel.easytravel.mdbspringboot.service.intf;

import com.easytravel.easytravel.mdbspringboot.model.POI;
import com.easytravel.easytravel.mdbspringboot.model.POI.Comment;
import java.util.List;

public interface POIService {
    List<POI> getPOIsByKeyword(String keyword);
    List<POI> getPOIsByAddress(String address);
    void removeFavoritePOI(String id);
    POI getPOIByID(Integer id);

    // added by Danny
    List<Comment> getPOICommentsByPoiId(Integer poiId);
    // added by Danny
    void insertPOICommentByPoiId(Integer poiId, Comment comment);
    // added by Danny
    Double getPOIAverageRating(Integer poiId);

    List<POI> getPOIs();
}
