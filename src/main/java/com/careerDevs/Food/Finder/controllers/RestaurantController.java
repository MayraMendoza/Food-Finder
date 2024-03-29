package com.careerDevs.Food.Finder.controllers;

import com.careerDevs.Food.Finder.models.LocationData;
import com.careerDevs.Food.Finder.models.Profile;
import com.careerDevs.Food.Finder.models.Restaurant;
import com.careerDevs.Food.Finder.repositories.ProfileRepository;
import com.careerDevs.Food.Finder.repositories.RestaurantRepository;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

//TODO - MAKE PROGRAM DRY & CONNECT TO REACT
// commit front end  to git



// testing userAuth branch
@CrossOrigin
@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private ProfileRepository profileRepository;

    @GetMapping("/test")
    public ResponseEntity<?> testRoute() {
        return new ResponseEntity<>("Restaurant test route", HttpStatus.OK);
    }

//    @GetMapping("/{symbol}")
//    public ResponseEntity<?> dynamicOverview(RestTemplate restTemplate, @PathVariable String symbol) {
//        try {
//
//            String url = BASE_URL + "&symbol=" + symbol + "&apikey=" + env.getProperty("AV_API_KEY");
//
//            Overview responseBody = restTemplate.getForObject(url, Overview.class);
//
//
//            if (responseBody == null) {
//                ApiError.throwErr(500, "Did not receive response from AV");
//
//
//            } else if (responseBody.getSymbol() == null) {
//                ApiError.throwErr(404, "Invalid stock symbol: " + symbol);
//
//            }
//            return ResponseEntity.ok(responseBody);
//
//        } catch (HttpClientErrorException e) {
//            return ApiError.customApiError(e.getMessage(), e.getStatusCode().value());
//        } catch (Exception e) {
//            return ApiError.genericApiError(e);
//        }
//    }

    @GetMapping("/test/long/{number}/{streetName}/{streetSuffix}/{city}/{state}")
    public ResponseEntity<?> longtest(RestTemplate restTemplate, @PathVariable String number,
                                      @PathVariable String streetName, @PathVariable String streetSuffix , @PathVariable String city,
                                      @PathVariable String state  ){
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + number + "+" + streetName +"+"+ streetSuffix +",+"+ city+ ",+"+ state+ "&key=";

        LocationData responseBody = restTemplate.getForObject(url, LocationData.class);

        return ResponseEntity.ok(responseBody);

    }



    // Create a restaurant profile
    @PostMapping("/")
    public ResponseEntity<?> createProfile(@RequestBody Restaurant newRestaurant) {
        Restaurant restaurant = restaurantRepository.save(newRestaurant);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    // add restaurant by profile/user
    @PostMapping("/profile/{profileId}")
    public ResponseEntity<?> createRestaurantByProfile(@PathVariable Long profileId, @RequestBody Restaurant newRestaurant) {
        Profile profile = profileRepository.findById(profileId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        newRestaurant.setProfile(profile);
        Restaurant restaurant = restaurantRepository.save(newRestaurant);
        return  new ResponseEntity<>(restaurant, HttpStatus.CREATED);


    }
    // get all restaurants
    @GetMapping("/")
    public ResponseEntity<?> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }


    /// get profile by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getProfileById(@PathVariable Long id){
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);

            if(restaurant.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(restaurant.get(), HttpStatus.OK);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity deleteById(@PathVariable Long id){
//        Restaurant restaurant = restaurantRepository.deleteById(id);
//
//            if(restaurant.isEmpty()){
//                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//            }
//            return new ResponseEntity<>("Restaurant:"+ id + "has been deleted", HttpStatus.OK)
//
//    }

    // query by field and value
    @GetMapping("/{field}/{value}")
    private ResponseEntity<?> getOverviewByField(@PathVariable String field, @PathVariable String value){
        try{
            List<Restaurant> foundRestaurant = null;
            field = field.toLowerCase();
            switch (field){
                case "name" -> foundRestaurant = restaurantRepository.findByName(value);
                case "id" -> foundRestaurant = restaurantRepository.findById(Long.parseLong(value));
                case "address" -> foundRestaurant = restaurantRepository.findByAddress(value);
                case "city" -> foundRestaurant= restaurantRepository.findByCity(value);
                case "state" -> foundRestaurant = restaurantRepository.findByState(value);
                case "zipcode" -> foundRestaurant = restaurantRepository.findByZipcode(value);
                case "phonenumber" -> foundRestaurant = restaurantRepository.findByPhoneNumber(value);
                case "cuisine" -> foundRestaurant = restaurantRepository.findByCuisine(value);


            }
            if(foundRestaurant == null || foundRestaurant.isEmpty()){
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "did not match any " + field);
            }
            return ResponseEntity.ok(foundRestaurant);

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

        //delete all
    @DeleteMapping("/all")
    public ResponseEntity<?> deleteAllFromDatabase(){
        try{
            //get total number of restaurant
            long totalRestaurants = restaurantRepository.count();
            if(totalRestaurants==0){
                return ResponseEntity.ok("There are no restaurants to delete");
            }
            restaurantRepository.deleteAll();
            return ResponseEntity.ok("deleted Overviews" + totalRestaurants);
        } catch (HttpClientErrorException e){
            return ResponseEntity.status(e.getStatusCode().value()).body(e.getMessage());

        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    // delete
    @DeleteMapping("/{filed}/{value}")
    private ResponseEntity<?> getRestaurantByField(@PathVariable String field, @PathVariable String value){
        try{
            List<Restaurant> foundRestaurant = null;
            field = field.toLowerCase();
            switch (field){
                case "id" -> foundRestaurant = restaurantRepository.findById(Long.parseLong(value));
                case "name" -> foundRestaurant = restaurantRepository.findByName(value);
                case "address" -> foundRestaurant = restaurantRepository.findByAddress(value);
                case "city" -> foundRestaurant = restaurantRepository.findByCity(value);
                case "state" -> foundRestaurant =restaurantRepository.findByState(value);
                case "zipcode" -> foundRestaurant =restaurantRepository.findByZipcode(value);
                case "phoneNumber" -> foundRestaurant = restaurantRepository.findByPhoneNumber(value);
                case "cuisine" -> foundRestaurant = restaurantRepository.findByCuisine(value);

            }
            if(foundRestaurant == null || foundRestaurant.isEmpty()){
                throw  new HttpClientErrorException(HttpStatus.BAD_REQUEST, "did not match any" + field);
            }
            return ResponseEntity.ok(foundRestaurant);

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
