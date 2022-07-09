package com.careerDevs.Food.Finder.repositories;

import com.careerDevs.Food.Finder.models.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem,Long> {
}
