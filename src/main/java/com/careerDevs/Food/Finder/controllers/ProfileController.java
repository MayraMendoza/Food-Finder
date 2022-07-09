package com.careerDevs.Food.Finder.controllers;

import com.careerDevs.Food.Finder.models.Profile;
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
@RequestMapping("/api/profiles")
public class ProfileController {
    @Autowired
    private ProfileRepository profileRepository;

    @GetMapping("/test")
    public ResponseEntity<?> testRoute(){
        return new ResponseEntity<>("profile route", HttpStatus.OK);
    }

    //Create a user profile
    @PostMapping("/")
    public ResponseEntity<?> createProfile(@RequestBody Profile newProfile){
        Profile profile =profileRepository.save(newProfile);
        return new ResponseEntity<>(profile, HttpStatus.CREATED);
    }

    //get all profiles
    @GetMapping("/")
    public ResponseEntity<List<Profile>> getAllProfiles(){
        List<Profile> profiles= profileRepository.findAll();
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    // get profile by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getProfileById( @PathVariable Long id){
        Optional<Profile> profile = profileRepository.findById(id);
        if(profile.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(profile.get(),HttpStatus.OK);



    }
}
