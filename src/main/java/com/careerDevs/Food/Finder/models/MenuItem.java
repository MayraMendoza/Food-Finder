package com.careerDevs.Food.Finder.models;
/**
 * Main Menu class will allow CRUD requests. Adding, editing, deleting and getting menu item data.
 * @Author Mayra Mendoza
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;

@Entity
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemName;
    private String description;
    private Float price;

    //TODO: LOOK INTO ENUM .


    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")

    private Restaurant restaurant;

    public MenuItem() {
    }


    public MenuItem(Long id, String itemName, String description, Float price) {
        this.id = id;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
