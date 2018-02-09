package com.capgemini.setrack.ian.controller;

import com.capgemini.setrack.ian.model.Airport;
import com.capgemini.setrack.ian.repository.AirportRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
@SpringBootTest
public class AirportControllerTest {

    @InjectMocks
    private AirportController airportController;

    @Mock
    private AirportRepository airportRepository;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(airportController).build();
    }

    @Test
    public void getAllAirports() throws Exception {
        List<Airport> airports = new ArrayList<Airport>();

        Airport airport1 = new Airport("Amsterdam", 3, 5);
        Airport airport2 = new Airport("London", 3, 4);

        airports.add(airport1);
        airports.add(airport2);

        when(airportRepository.findAll()).thenReturn(airports);

        this.mockMvc.perform(get("/api/airports/"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void testCreateAirportWithInvalidMaxAirplanesExpectError() throws Exception {

        Airport airport1 = new Airport("Dubai", 10, 6);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(airport1);

        Mockito.when(airportRepository.save(Mockito.any(Airport.class))).thenReturn(airport1);

        this.mockMvc.perform(post("/api/airports/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().is4xxClientError());

        Mockito.verify(airportRepository, times(0)).save(Mockito.any(Airport.class));
    }

    @Test
    public void testDeleteAirportIsOk() throws Exception {

        Mockito.doNothing().when(airportRepository).delete(Mockito.anyLong());

        this.mockMvc.perform(delete("/api/airports/delete/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        Mockito.verify(airportRepository, times(1)).delete((long) 1);
    }

    @Test
    public void testCreateAirport() throws Exception {

        Airport airport1 = new Airport("New York", 3, 8);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(airport1);

        Mockito.when(airportRepository.save(Mockito.any(Airport.class))).thenReturn(airport1);

        this.mockMvc.perform(post("/api/airports/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(jsonPath("$.id", is((int) airport1.getId())))
                .andExpect(jsonPath("$.name", is(airport1.getName())))
                .andExpect(jsonPath("$.maxAirplanes", is(airport1.getMaxAirplanes())))
                .andExpect(jsonPath("$.numberRunway", is(airport1.getNumberRunway())))
                .andExpect(status().isOk());

        Mockito.verify(airportRepository, times(1)).save(Mockito.any(Airport.class));
    }

}