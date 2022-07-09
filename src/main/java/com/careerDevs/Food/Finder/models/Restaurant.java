package com.careerDevs.Food.Finder.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String state;
    private String zipcode;
    private String phoneNumber;
    private String cuisine;


    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)

    @JsonIgnore
    private Set<MenuItem> menuItem;

    public Restaurant() {
    }

    public Restaurant(Long id, String name, String address, String state, String zipcode, String phoneNumber, String cuisine) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.state = state;
        this.zipcode = zipcode;
        this.phoneNumber = phoneNumber;
        this.cuisine = cuisine;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public Set<MenuItem> getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(Set<MenuItem> menuItem) {
        this.menuItem = menuItem;
    }
}
