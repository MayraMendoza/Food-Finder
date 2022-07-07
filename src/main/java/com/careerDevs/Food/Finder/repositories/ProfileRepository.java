package com.careerDevs.Food.Finder.repositories;

import com.careerDevs.Food.Finder.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
