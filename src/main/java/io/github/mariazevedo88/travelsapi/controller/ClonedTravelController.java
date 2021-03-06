package io.github.mariazevedo88.travelsapi.controller;

import java.net.URI;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.mariazevedo88.travelsapi.model.ClonedTravel;
import io.github.mariazevedo88.travelsapi.service.ClonedTravelService;

@RestController
@RequestMapping("/api-travels/cloned/travels")
public class ClonedTravelController {
    
    private static final Logger logger = Logger.getLogger(ClonedTravelController.class);

    @Autowired
    private ClonedTravelService tripService;

    @GetMapping
    public ResponseEntity<List<ClonedTravel>> find() {

        if (tripService.find().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        logger.info(tripService.find());

        return ResponseEntity.ok(tripService.find());

    }

    @DeleteMapping
    public ResponseEntity<Boolean> delete() {
        try {
            tripService.delete();
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ClonedTravel> create(@RequestBody JSONObject trip) {
        try {
            if (tripService.isJSONValid(trip.toString())) {
                ClonedTravel tripCreated = tripService.create(trip);
                URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path(tripCreated.getOrderNumber()).build().toUri();
                
                if (tripService.isStartDateGreaterThanEndDate(tripCreated)) {
                    logger.error("The start date is greater than end date.");
                    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
                } else {
                    tripService.add(tripCreated);
                    return ResponseEntity.created(uri).body(null);
                }
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            logger.error("JSON fields are not parsable. " + e);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

    @PutMapping(path = "/{id}", produces = {"application/json"})
    public ResponseEntity<ClonedTravel> update(@PathVariable("id") long id, @RequestBody JSONObject travel) {
        try {
            if (tripService.isJSONValid(travel.toString())) {
                ClonedTravel tripUpdate = tripService.findById(id);

                if (tripUpdate == null) {
                    logger.error("Travel not found.");
                    return ResponseEntity.notFound().build();
                } else {
                    ClonedTravel tripUpdated = tripService.update(tripUpdate, travel);
                    return ResponseEntity.ok(tripUpdated);
                }
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            logger.error("JSON fields are not parsable." + e);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }   
    }
    



}
