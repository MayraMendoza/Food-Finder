package com.careerDevs.Food.Finder.controllers;

import com.careerDevs.Food.Finder.models.Critique;
import com.careerDevs.Food.Finder.models.Profile;
import com.careerDevs.Food.Finder.repositories.CritiqueRepository;
import com.careerDevs.Food.Finder.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/critiques")
public class CritiqueController {
    @Autowired
    private CritiqueRepository critiqueRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @GetMapping("/test")
    public ResponseEntity<?> testRoute(){
        return new ResponseEntity<>("critique route", HttpStatus.OK);
    }
// profile id
    @PostMapping("/{profileId}")
    public ResponseEntity<?> createCritique(@PathVariable Long profileId, @RequestBody Critique newCritique){
        Profile profile = profileRepository.findById(profileId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        newCritique.setProfile(profile);
        Critique critique = critiqueRepository.save(newCritique);
        return new ResponseEntity<>(critique, HttpStatus.CREATED);
    }

    //Get all Critiques
    @GetMapping("/")
    public ResponseEntity<?> getAllCritiques(){
        List<Critique> critiques = critiqueRepository.findAll();
        return new ResponseEntity<>(critiques, HttpStatus.OK);
    }

    // get critique by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getCritiqueById(@PathVariable Long id){
        Optional <Critique> maybeCritique =  critiqueRepository.findById(id);
        if(maybeCritique.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(maybeCritique.get(), HttpStatus.OK);
    }

    //Get all critiques made by a specific profile
    @GetMapping("/profile/{profileId}")
    public ResponseEntity<List<Critique>> getCritiquesByprofile(@PathVariable Long profileId){
        List<Critique> critiques = critiqueRepository.findAllByprofile_id(profileId);
        return new ResponseEntity<>(critiques, HttpStatus.OK);
    }

}
