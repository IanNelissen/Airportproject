package com.capgemini.setrack.ian.model;

import javax.persistence.*;

@Entity
public class Airplane {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String type;
    private int maxPassengers;
    private int maxFuel;

    public Airplane(){}

    public Airplane(String type, int maxPassengers, int maxFuel) {
        this.type = type;
        this.maxPassengers = maxPassengers;
        this.maxFuel = maxFuel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public void setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    public int getMaxFuel() {
        return maxFuel;
    }

    public void setMaxFuel(int maxFuel) {
        this.maxFuel = maxFuel;
    }
}
