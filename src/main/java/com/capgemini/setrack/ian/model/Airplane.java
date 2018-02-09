package com.capgemini.setrack.ian.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Airplane {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @NotNull(message="Need airplane Type")
    @Size(min=2, max=20, message="A type must be between 2 and 12 characters long!")
    private String airplaneType;

    @NotNull(message="Need number of passengers")
    @Max(500)
    private int numberPassengers;

    @NotNull(message = "Needs number of fuel")
    @Max(10)
    private int maxFuel;

    @NotNull
    @Min(2)
    private int fuelLeft;

    public Airplane(){}

    public Airplane(String airplaneType, int numberPassengers, int maxFuel, int fuelLeft) {
        this.airplaneType = airplaneType;
        this.numberPassengers = numberPassengers;
        this.maxFuel = maxFuel;
        this.fuelLeft = fuelLeft;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAirplaneType() {
        return airplaneType;
    }

    public void setAirplaneType(String type) {
        this.airplaneType = type;
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

    public int getFuelLeft() {
        return fuelLeft;
    }
    public void setFuelLeft(int fuelLeft) {
        this.fuelLeft = fuelLeft;
    }
}
