package com.capgemini.setrack.ian.controller;

import com.capgemini.setrack.ian.model.Airport;
import com.capgemini.setrack.ian.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/airports/")
public class AirportController {
    @Autowired
    private AirportRepository airportRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Airport> getAllAirports(){
        return this.airportRepository.findAll();
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public Airport createAirPort(@RequestBody Airport airport){
        this.airportRepository.save(airport);
        return airport;
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public Airport updateAirport(@RequestBody Airport airport){
        this.airportRepository.save(airport);
        return airport;
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public void deleteAirplane(@PathVariable long id) {
        this.airportRepository.delete(id);
    }
}



