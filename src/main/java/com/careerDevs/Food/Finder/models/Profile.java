package com.careerDevs.Food.Finder.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Optional;
import java.util.Set;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private String favoriteFood;
    private boolean isOwner;


    @OneToMany(mappedBy = "profile", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Critique> critique; //Having a set will avoid dup;ocates

    // connecting restaurant with a profile
    @OneToOne(optional = true, mappedBy = "profile")
    @JsonIgnoreProperties("profile")
    private Restaurant restaurant;

    public Profile() {
    }

    public Profile(Long id, String name, Integer age, String favoriteFood, boolean isOwner) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.favoriteFood = favoriteFood;
        this.isOwner = isOwner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getFavoriteFood() {
        return favoriteFood;
    }

    public void setFavoriteFood(String favoriteFood) {
        this.favoriteFood = favoriteFood;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean owner) {
        isOwner = owner;
    }

    public Set<Critique> getCritique() {
        return critique;
    }

    public void setCritique(Set<Critique> critique) {
        this.critique = critique;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
