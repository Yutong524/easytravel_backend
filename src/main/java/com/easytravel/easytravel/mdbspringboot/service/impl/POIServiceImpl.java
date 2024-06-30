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
        return poiRepository.getPOIByPoiId(id);
    }

    // added by Danny
    @Override
    public List<Comment> getPOICommentsByPoiId(Integer poiId) {
        //return poiRepository.getPOICommentsByPoiId(poiId);
        return new ArrayList<Comment>();
    }
    //added by Danny
    @Override
    public void insertPOICommentByPoiId(Integer poiId, Comment comment) {
        //poiRepository.insertPOICommentByPoiId(poiId, comment);
    }
    //added by Danny
    @Override
    public Double getPOIAverageRating(Integer poiId) {
        //List<Comment> comments = poiRepository.getPOICommentsByPoiId(poiId);
        //if (comments.isEmpty()) {
        //    return 0.0;
        //}
       // Double sum = 0.0;
        //for (Comment comment : comments) {
        //    sum += comment.getRating();
        //}
        //return sum / comments.size();
        return 0.0;
    }

    @Override
    public List<POI> getPOIs() {
        return poiRepository.findAll();
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
