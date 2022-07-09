package com.careerDevs.Food.Finder.repositories;

import com.careerDevs.Food.Finder.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
