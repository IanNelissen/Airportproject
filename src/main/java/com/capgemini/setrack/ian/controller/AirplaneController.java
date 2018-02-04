package com.capgemini.setrack.ian.controller;

import com.capgemini.setrack.ian.model.Airplane;
import com.capgemini.setrack.ian.repository.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/airplanes/")
public class AirplaneController {
        @Autowired
        private AirplaneRepository airplaneRepository;

        //get all airplanes
        @RequestMapping(value = "", method = RequestMethod.GET)
        public Iterable<Airplane> getAllAirplanes(){
            return this.airplaneRepository.findAll();
        }

        //create new airplane
        @RequestMapping(value = "create", method = RequestMethod.POST)
        public Airplane createAirplane(@RequestBody Airplane airplane){
               this.airplaneRepository.save(airplane);
               return airplane;
        }

        //update airplane
        @RequestMapping(value = "edit", method = RequestMethod.POST)
        public Airplane updateAirplane(@RequestBody Airplane airplane){
            this.airplaneRepository.save(airplane);
            return airplane;
        }

        //delete airplane
        @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
        public void deleteAirplane(@PathVariable long id) {
            this.airplaneRepository.delete(id);
        }
    }



