package com.careerDevs.Food.Finder.repositories;

import com.careerDevs.Food.Finder.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByName(String name);
    List<Restaurant> findById(long id);
    List<Restaurant> findByAddress(String address);
    List<Restaurant> findByCity(String city);
    List<Restaurant> findByState(String state);
    List<Restaurant> findByZipcode(String zipcode);
    List<Restaurant> findByPhoneNumber(String phoneNumber);
    List<Restaurant> findByCuisine(String cuisine);

}
