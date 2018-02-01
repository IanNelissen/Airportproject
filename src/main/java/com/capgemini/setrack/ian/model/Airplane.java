package com.capgemini.setrack.ian.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Airplane {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @NotNull(message="Need airplane Type")
    @Size(min=2, max=12, message="A type must be between 2 and 12 characters long!")
    private String type;

    @NotNull(message="Need number of passengers")
    @Max(500)
    private int numberPassengers;

    @NotNull(message = "Needs number of fuel")
    @Max(5)
    private int maxFuel;

    public Airplane(){}

    public Airplane(String type, int numberPassengers, int maxFuel) {
        this.type = type;
        this.numberPassengers = numberPassengers;
        this.maxFuel = 5;
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

    public int getNumberPassengers() {
        return numberPassengers;
    }

    public void setNumberPassengers(int numberPassengers) {
        this.numberPassengers = numberPassengers;
    }

    public int getMaxFuel() {
        return maxFuel;
    }

    public void setMaxFuel(int maxFuel) {
        this.maxFuel = maxFuel;
    }
}
