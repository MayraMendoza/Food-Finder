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

    List<Restaurant> deleteByName(String name);
    List<Restaurant> deleteById(long id);
    List<Restaurant> deleteByAddress(String address);
    List<Restaurant> deleteByCity(String city);
    List<Restaurant> deleteByState(String state);
    List<Restaurant> deleteByZipcode(String zipcode);
    List<Restaurant> deleteByPhoneNumber(String phoneNumber);
    List<Restaurant> deleteByCuisine(String cuisine);


}
