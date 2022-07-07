package com.careerDevs.Food.Finder.models;

import javax.persistence.*;

//class to comment
@Entity
public class Critique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String body;
    // TODO ask if rating would be its own separate class.
    // what should i be looking for?
    // boolean button ? true = 5?
    // click on a button 1-5?

    private String rating;

}
