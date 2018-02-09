package com.capgemini.setrack.ian.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Airport {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @NotNull(message="Needs a name")
    @Size(min=3, max=15, message="A name must be between 3 and 15 characters long!")
    private String name;

    @NotNull(message="Add number of airplanes stationed at airport.")
    @Max(value=3, message="No more then 3 planes are allowed on  airport")
    private int maxAirplanes;

    @NotNull(message="A number of runways must be selected")
    @Max(value=10, message="Maximum of 10 runway tracks")
    private int numberRunway;


    public Airport(){}

    public Airport(String name, int maxAirplanes, int numberRunway) {
        this.name = name;
        this.maxAirplanes = 3;
        this.numberRunway = numberRunway;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxAirplanes() {
        return maxAirplanes;
    }

    public void setMaxAirplanes(int maxAirplanes) {
        this.maxAirplanes = maxAirplanes;
    }

    public int getNumberRunway() {
        return numberRunway;
    }

    public void setNumberRunway(int numberRunway) {
        this.numberRunway = numberRunway;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
