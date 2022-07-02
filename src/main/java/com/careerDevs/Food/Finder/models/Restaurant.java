package com.careerDevs.Food.Finder.models;

import javax.persistence.*;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String name;
    private String address;
    private String state;
    private String zipcode;
    private String phoneNumber;
    private String cuisine;



}
