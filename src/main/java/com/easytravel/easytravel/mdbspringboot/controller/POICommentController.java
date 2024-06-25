package com.easytravel.easytravel.mdbspringboot.controller;

import com.easytravel.easytravel.mdbspringboot.model.POI;
import com.easytravel.easytravel.mdbspringboot.model.POI.Comment;
import com.easytravel.easytravel.mdbspringboot.service.impl.POIServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//added by Danny
@RestController
@RequestMapping("/api/poiComments")
public class POICommentController {
    @Autowired
    private POIServiceImpl poiService;

    @GetMapping("/{poiId}")
    public List<Comment> getPOICommentsByPoiId(@PathVariable Integer poiId) {
        return poiService.getPOICommentsByPoiId(poiId);
    }

    @PostMapping("/{poiId}")
    public void insertPOICommentByPoiId(@PathVariable Integer poiId, @RequestBody Comment comment) {
        poiService.insertPOICommentByPoiId(poiId, comment);
    }

    @GetMapping("/averageRating/{poiId}")
    public Double getPOIAverageRating(@PathVariable Integer poiId) {
        return poiService.getPOIAverageRating(poiId);
    }
}
