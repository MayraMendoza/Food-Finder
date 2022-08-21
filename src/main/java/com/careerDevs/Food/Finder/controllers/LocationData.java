package com.careerDevs.Food.Finder.controllers;


import com.careerDevs.Food.Finder.repositories.LocationDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@CrossOrigin
@RestController
@RequestMapping("/api/location")


public class LocationData {

    @Autowired
    private LocationDataRepository locationDataRepository;
    @GetMapping("/new/test/{number}/{streetName}/{streetSuffix}/{city}/{state}")
    public ResponseEntity<?> radarTest(RestTemplate restTemplate, @RequestHeader String authorization,
                                       @PathVariable String number,
                                       @PathVariable String streetName, @PathVariable String streetSuffix , @PathVariable String city,
                                       @PathVariable String state){
        String url ="http://api.radar.io/v1/geocode/forward?query=" +number+"+"+ streetName +"+" + streetSuffix + "+"+ city + "+" + state;
        Object responseBody = restTemplate.getForObject(url, Object.class);

        System.out.println(authorization);

        return ResponseEntity.ok(responseBody);

    }

}
