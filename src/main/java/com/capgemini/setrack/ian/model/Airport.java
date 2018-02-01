package com.capgemini.setrack.ian.model;

import javax.persistence.*;

@Entity
public class Airport {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String name;
    private int maxAirplanes;
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
