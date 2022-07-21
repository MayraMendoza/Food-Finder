package com.careerDevs.Food.Finder.controllers;

import com.careerDevs.Food.Finder.models.Critique;
import com.careerDevs.Food.Finder.models.MenuItem;
import com.careerDevs.Food.Finder.models.Profile;
import com.careerDevs.Food.Finder.models.Restaurant;
import com.careerDevs.Food.Finder.repositories.MenuItemRepository;
import com.careerDevs.Food.Finder.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/menuitems")
public class MenuItemController {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/test")
    public ResponseEntity<?> testRoute(){
        return new ResponseEntity<>("MenuItem test route", HttpStatus.OK);
    }

    // create a menuItem
//    @PostMapping("/")
//    public ResponseEntity<?> createMenuItem(@RequestBody MenuItem newMenuItem){
//        MenuItem menuItem = menuItemRepository.save(newMenuItem);
//        return new ResponseEntity<>(menuItem, HttpStatus.CREATED);
//    }

    // create menu item by restaurant
    @PostMapping("/restaurant/{RestaurantId}")
    public ResponseEntity<MenuItem> createMenuItem(@PathVariable Long RestaurantId, @RequestBody MenuItem newMenuItem){
        Restaurant restaurant = restaurantRepository.findById(RestaurantId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        newMenuItem.setRestaurant(restaurant);
        MenuItem menuItem = menuItemRepository.save(newMenuItem);
        return new ResponseEntity<>(menuItem, HttpStatus.CREATED);
    }

    // get all restaurants
    @GetMapping("/")
    public ResponseEntity<?> getAllRestaurants(){
        List<MenuItem> menuItems = menuItemRepository.findAll();
        return new ResponseEntity<>(menuItems, HttpStatus.OK);
    }

    // get all menu items by restaurant
    @GetMapping("/Restaurant/{restaurantId}")
    public ResponseEntity<List<MenuItem>> getMenuByRestaurant(@PathVariable Long restaurantId){
        List<MenuItem> menuItems =menuItemRepository.findAllByRestaurant_id(restaurantId);
        return  new ResponseEntity<>(menuItems, HttpStatus.OK);

    }

    @GetMapping("/{field}/{value}")
    private ResponseEntity<?> getMenuItemByField(@PathVariable String field, @PathVariable String value){
        try{
            List<MenuItem> foundMenuItem = null;
            field = field.toLowerCase();
            switch (field){
                case "itemName" -> foundMenuItem = menuItemRepository.findByItemName(value);
                case "id" -> foundMenuItem = menuItemRepository.findById(Long.parseLong(value));
                case "description" -> foundMenuItem = menuItemRepository.findByDescription(value);
                case "price" -> foundMenuItem = menuItemRepository.findByPrice(Float.parseFloat(value));



            }
            if(foundMenuItem == null || foundMenuItem.isEmpty()){
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "did not match any " + field);
            }
            return ResponseEntity.ok(foundMenuItem);

        } catch (NumberFormatException e){
            return ResponseEntity.status(400).body("please enter a number");
        }catch (HttpClientErrorException e){
            return ResponseEntity.status(e.getStatusCode().value()).body(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }


    }
}


