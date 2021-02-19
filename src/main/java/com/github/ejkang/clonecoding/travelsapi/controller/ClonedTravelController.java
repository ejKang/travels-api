package com.github.ejkang.clonecoding.travelsapi.controller;

import java.util.List;

import com.github.ejkang.clonecoding.travelsapi.model.ClonedTravel;
import com.github.ejkang.clonecoding.travelsapi.service.ClonedTravelService;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-travels/cloned/travels")
public class ClonedTravelController {
    
    private static final Logger logger = Logger.getLogger(ClonedTravelController.class);

    @Autowired
    private ClonedTravelService tripService;

    @GetMapping
    public ResponseEntity<List<ClonedTravel>> find() {

        return null;

    }

    @DeleteMapping
    public ResponseEntity<Boolean> delete() {
        
        return null;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ClonedTravel> create() {
        
        return null;
    }

    @PutMapping(path = "/{id}", produces = {"application/json"})
    public ResponseEntity<ClonedTravel> update(@PathVariable("id") long id, @RequestBody JSONObject travel) {
        

        return null;
    }
    



}
