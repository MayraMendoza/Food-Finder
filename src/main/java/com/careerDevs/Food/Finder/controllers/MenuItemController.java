package com.careerDevs.Food.Finder.controllers;

import com.careerDevs.Food.Finder.models.MenuItem;
import com.careerDevs.Food.Finder.repositories.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/menuitems")
public class MenuItemController {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @GetMapping("/test")
    public ResponseEntity<?> testRoute(){
        return new ResponseEntity<>("MenuItem test route", HttpStatus.OK);
    }

    // create a menuItem
    @PostMapping("/")
    public ResponseEntity<?> createMenuItem(@RequestBody MenuItem newMenuItem){
        MenuItem menuItem = menuItemRepository.save(newMenuItem);
        return new ResponseEntity<>(menuItem, HttpStatus.CREATED);
    }

    // get all restaurants
    @GetMapping("/")
    public ResponseEntity<?> getAllRestaurants(){
        List<MenuItem> menuItems = menuItemRepository.findAll();
        return new ResponseEntity<>(menuItems, HttpStatus.OK);
    }
}


