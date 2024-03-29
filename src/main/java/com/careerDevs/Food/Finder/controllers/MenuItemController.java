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
import java.util.Optional;

/**
 * MenuItem controller hosts CRUD requests that will be used to post, update, delete and get menu
 * items.
 * @Author Mayra Mendoza 07/20/22
 */

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

    //get menu item by id
//    @GetMapping("/{Id}")
//    public ResponseEntity<?> getMenuItemByid(@PathVariable Long id){
//        Optional<MenuItem> menuItemSearch = menuItemRepository.findById(Long.parseLong(String.valueOf(id)));
//        return new ResponseEntity<>(menuItemSearch,HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getmenuItemById(@PathVariable Long id){
        Optional<MenuItem> menuItemr = menuItemRepository.findById(id);

        if(menuItemr.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(menuItemr.get(), HttpStatus.OK);
    }


    /*
    * localhost:8080/api/menuitems/update/{id}
    * This endpoint will allow menu items to be updated by id
     */

    @PutMapping("/update/{id}")
    MenuItem updateItem(@RequestBody MenuItem newMenuItem, @PathVariable Long id ){
        return menuItemRepository.findById(id).map(MenuItem -> {
            MenuItem.setItemName(newMenuItem.getItemName());
            MenuItem.setDescription(newMenuItem.getDescription());
            MenuItem.setPrice(newMenuItem.getPrice());
            return menuItemRepository.save(MenuItem);
                })
                .orElseGet(()->{
                    newMenuItem.setId(id);
                    return menuItemRepository.save(newMenuItem);
                });

    }


//    @GetMapping("/{field}/{value}")
//    private ResponseEntity<?> getMenuItemByField(@PathVariable String field, @PathVariable String value){
//        try{
//            List<MenuItem> foundMenuItem = null;
//            field = field.toLowerCase();
//            switch (field){
//                case "itemName" -> foundMenuItem = menuItemRepository.findByItemName(value);
//                case "id" -> foundMenuItem = menuItemRepository.findById(Long.parseLong(value));
//                case "description" -> foundMenuItem = menuItemRepository.findByDescription(value);
//                case "price" -> foundMenuItem = menuItemRepository.findByPrice(Float.parseFloat(value));
//
//
//
//            }
//            if(foundMenuItem == null || foundMenuItem.isEmpty()){
//                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "did not match any " + field);
//            }
//            return ResponseEntity.ok(foundMenuItem);
//
//        } catch (NumberFormatException e){
//            return ResponseEntity.status(400).body("please enter a number");
//        }catch (HttpClientErrorException e){
//            return ResponseEntity.status(e.getStatusCode().value()).body(e.getMessage());
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//            System.out.println(e.getClass());
//            return ResponseEntity.internalServerError().body(e.getMessage());
//        }
//
//
//    }

//    @PutMapping("/updatMenuItem/{}")
}


