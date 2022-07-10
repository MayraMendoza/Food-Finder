package com.careerDevs.Food.Finder.controllers;

import com.careerDevs.Food.Finder.models.Restaurant;
import com.careerDevs.Food.Finder.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/test")
    public ResponseEntity<?> testRoute() {
        return new ResponseEntity<>("Restaurant test route", HttpStatus.OK);
    }

    // Create a restaurant profile
    @PostMapping("/")
    public ResponseEntity<?> createProfile(@RequestBody Restaurant newRestaurant) {
        Restaurant restaurant = restaurantRepository.save(newRestaurant);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    // get all restaurants
    @GetMapping("/")
    public ResponseEntity<?> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getProfileById(@PathVariable Long id){
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);

            if(restaurant.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(restaurant.get(), HttpStatus.OK);
    }
}
