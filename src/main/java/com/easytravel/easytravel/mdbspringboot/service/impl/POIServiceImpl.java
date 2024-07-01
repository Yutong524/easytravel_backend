package com.easytravel.easytravel.mdbspringboot.service.impl;

import com.easytravel.easytravel.mdbspringboot.model.Customer;
import com.easytravel.easytravel.mdbspringboot.model.POI;
import com.easytravel.easytravel.mdbspringboot.repository.CustomerRepository;
import com.easytravel.easytravel.mdbspringboot.repository.POIRepository;
import com.easytravel.easytravel.mdbspringboot.service.intf.POIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.easytravel.easytravel.mdbspringboot.model.POI.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class POIServiceImpl implements POIService {
    @Autowired
    private POIRepository poiRepository;
    @Autowired
    private CustomerRepository customerRepository;

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
    public POI getPOIByID(Integer id) {
        return poiRepository.findByPoiId(id);
    }

    // added by Danny
    @Override
    public List<Comment> getPOICommentsByPoiId(Integer poiId) {
        //return poiRepository.getPOICommentsByPoiId(poiId);
        return new ArrayList<Comment>();
    }

    //added by Danny
    @Override
    public Double insertPOICommentByPoiId(Integer poiId, Map<String, Object> comment) {
        POI poi = poiRepository.getPOIByPoiId(poiId);
        List<Object> comments = poi.getComments();
        Integer oldCommentCount = comments.size();
        Double oldRating = poi.getRating();
        comments.add(comment);
        poi.setComments(comments);

        //deal with rating
        double commentRating = ((Number) comment.get("rating")).doubleValue();
        Double newRating = (oldRating * oldCommentCount + commentRating) / (oldCommentCount + 1);
        poi.setRating(newRating);
        poiRepository.save(poi);

        return newRating;
    }

    @Override
    public List<POI> getPOIs() {
        return poiRepository.findAll();
    }

    @Override
    public POI getPOIByPOIName(String poiName) {
        return poiRepository.getPOIByName(poiName);
    }

    public String toggleFavoritePOI(Integer customerId, Integer poiId) {
        Customer customer = customerRepository.getCustomerById(customerId);
        List<Integer> poiIds = customer.getFavorite_poi();

        if (poiIds.contains(poiId)) {
            poiIds.remove(poiId);
        } else {
            poiIds.add(poiId);
        }
        customer.setFavorite_poi(poiIds);
        customerRepository.save(customer);
        return "success";
    }
}
