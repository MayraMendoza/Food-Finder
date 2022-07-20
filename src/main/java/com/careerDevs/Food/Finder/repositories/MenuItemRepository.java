package com.careerDevs.Food.Finder.repositories;

import com.careerDevs.Food.Finder.models.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem,Long> {
    List<MenuItem> findById(long id);
    List<MenuItem> findByItemName(String itemName);
    List<MenuItem> findByDescription(String description);
    List<MenuItem> findByPrice(float price);
    List<MenuItem> findAllByRestaurant_id(Long restaurant_id);

    List<MenuItem> deleteById(long id);
    List<MenuItem> deleteByItemName(String itemName);
    List<MenuItem> deleteByDescription(String description);
    List<MenuItem> deleteById(float price);

}
