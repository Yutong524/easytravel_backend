package com.easytravel.easytravel.mdbspringboot.controller;

import com.easytravel.easytravel.mdbspringboot.model.POI;
import com.easytravel.easytravel.mdbspringboot.model.POI.Comment;
import com.easytravel.easytravel.mdbspringboot.service.impl.POIServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

//added by Danny
@RestController
@RequestMapping("/api/pois")
public class POICommentController {
    @Autowired
    private POIServiceImpl poiService;

    @GetMapping("/comments/{poiId}")
    public List<Comment> getPOICommentsByPoiId(@PathVariable Integer poiId) {
        return poiService.getPOICommentsByPoiId(poiId);
    }

    @PostMapping("/comments/{poiId}")
    public Double insertPOICommentByPoiId(@PathVariable Integer poiId, @RequestBody Map<String, Object> comment) {
        return poiService.insertPOICommentByPoiId(poiId, comment);
    }

}
