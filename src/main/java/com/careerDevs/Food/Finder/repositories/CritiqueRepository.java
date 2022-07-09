package com.careerDevs.Food.Finder.repositories;

import com.careerDevs.Food.Finder.models.Critique;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CritiqueRepository extends JpaRepository<Critique, Long> {
    List<Critique> findAllByprofile_id(Long profile_id);

}
