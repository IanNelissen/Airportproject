package com.capgemini.setrack.ian.controller;

import com.capgemini.setrack.ian.model.Airplane;
import com.capgemini.setrack.ian.model.Airport;
import com.capgemini.setrack.ian.repository.AirplaneRepository;
import com.capgemini.setrack.ian.repository.AirportRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
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
public class AirplaneControllerTest {

    @InjectMocks
    private AirplaneController airplaneController;

    @Mock
    private AirplaneRepository airplaneRepository;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(airplaneController).build();
    }

    @Test
    public void getAllAirplanes() throws Exception {
        List<Airplane> airplanes = new ArrayList<Airplane>();

        Airplane airplane1 = new Airplane("Boeing", 250, 5, 2);
        Airplane airplane2 = new Airplane("Cesna", 5, 5,2);

        airplanes.add(airplane1);
        airplanes.add(airplane2);

        when(airplaneRepository.findAll()).thenReturn(airplanes);

        this.mockMvc.perform(get("/api/airplanes/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateAirplane() throws Exception {

        Airplane airplane1 = new Airplane("Learjet", 120, 5, 2);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(airplane1);

        Mockito.when(airplaneRepository.save(Mockito.any(Airplane.class))).thenReturn(airplane1);

        this.mockMvc.perform(post("/api/airplanes/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(jsonPath("$.id", is((int) airplane1.getId())))
                .andExpect(jsonPath("$.type", is(airplane1.getAirplaneType())))
                .andExpect(jsonPath("$.numberPassengers", is(airplane1.getNumberPassengers())))
                .andExpect(jsonPath("$.maxFuel", is(airplane1.getMaxFuel())))
                .andExpect(jsonPath("$.fuelLeft", is(airplane1.getFuelLeft())))
                .andExpect(status().isOk());

        Mockito.verify(airplaneRepository, times(1)).save(Mockito.any(Airplane.class));
    }

    @Test
    public void testDeleteAirplaneIsOk() throws Exception {

        Mockito.doNothing().when(airplaneRepository).delete(Mockito.anyLong());

        this.mockMvc.perform(delete("/api/airplanes/delete/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        Mockito.verify(airplaneRepository, times(1)).delete((long) 1);
    }
}