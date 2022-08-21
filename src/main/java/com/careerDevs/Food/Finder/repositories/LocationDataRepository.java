package com.careerDevs.Food.Finder.repositories;

import com.careerDevs.Food.Finder.models.LocationData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationDataRepository extends JpaRepository<LocationData, Long> {
}
