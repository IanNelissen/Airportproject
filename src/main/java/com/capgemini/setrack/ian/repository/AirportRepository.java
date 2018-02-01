package com.capgemini.setrack.ian.repository;

import com.capgemini.setrack.ian.model.Airport;
import org.springframework.data.repository.CrudRepository;

public interface AirportRepository extends CrudRepository<Airport, Long>{}