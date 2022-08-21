package com.careerDevs.Food.Finder.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class LocationData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable= false, unique =true)
    private long id;

    @JsonProperty("latitude")
    @Column(name="latitude", nullable = false)
    private Float Latitude;

    @JsonProperty("longitude")
    @Column( name= "longitude", nullable = false)
    private Float longitude;

    public LocationData() {
    }

    public LocationData(long id, Float latitude, Float longitude) {
        this.id = id;
        Latitude = latitude;
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Float getLatitude() {
        return Latitude;
    }

    public void setLatitude(Float latitude) {
        Latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }
}
