package com.capgemini.setrack.ian.repository;


import com.capgemini.setrack.ian.model.Airplane;
import org.springframework.data.repository.CrudRepository;

public interface AirplaneRepository extends CrudRepository<Airplane, Long>{}