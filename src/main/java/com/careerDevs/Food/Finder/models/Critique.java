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
    // can this be updated?


    private String rating;
    // One profile/user can make many critiques
    @ManyToOne
    @JoinColumn(name ="profile_id", referencedColumnName = "id")

    private Profile profile;

    // default constructor
    public Critique() {
    }

    public Critique(Long id, String title, String body, String rating, Critique critique) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.rating = rating;
        this.profile = profile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
