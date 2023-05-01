package br.edu.utfpr.td.tsi.api.controller;

import java.util.List;

import br.edu.utfpr.td.tsi.api.exception.NoDataFoundException;
import br.edu.utfpr.td.tsi.api.exception.NotFoundException;
import br.edu.utfpr.td.tsi.api.model.Vehicle;
import br.edu.utfpr.td.tsi.api.service.VehicleService;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(VehicleController.class)
public class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleService service;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Vehicle> vehicles;

    @BeforeEach
    public void init() {
        vehicles = List.of(
                Vehicle.build(1L, 2012, "PRETO", "FIAT", "CARRO", "UNO", null, null),
                Vehicle.build(1L, 2014, "VERDE", "FIAT", "CARRO", "PALIO", null, null),
                Vehicle.build(1L, 2013, "PRETO", "HONDA", "MOTO", "TITAN", null, null)
        );
    }

    @Test
    public void testFindVehicleNotFound() throws Exception {

        Mockito.when(service.findByLicensePlate("ABC1D23"))
                .thenThrow(new NotFoundException("Vehicle not found"));

        mockMvc.perform(get("/vehicles/ABC1D23")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("Vehicle not found"));
    }

    @Test
    public void testFindVehicleFound() throws Exception {

        Mockito.when(service.findByLicensePlate("ABC1D24"))
                .thenReturn(vehicles.get(0));

        mockMvc.perform(get("/vehicles/ABC1D24")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(vehicles.get(0))));
    }

    @Test
    public void testFindAllVehiclesNotFound() throws Exception {

        Mockito.when(service.findAll())
                .thenThrow(new NoDataFoundException("No vehicles found"));

        mockMvc.perform(get("/vehicles")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("No vehicles found"));
    }

    @Test
    public void testFindAllVehiclesFound() throws Exception {

        Mockito.when(service.findAll())
                .thenReturn(vehicles);

        mockMvc.perform(get("/vehicles")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(vehicles)));
    }

    @Test
    public void testFindVehiclesByAttributesFound() throws Exception {

        List<Vehicle> cars = List.of(vehicles.get(0), vehicles.get(1));

        Mockito.when(service.findByAttributes(null, "CARRO"))
                .thenReturn(cars);

        mockMvc.perform(get("/vehicles/search")
                .param("type", "CARRO")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(cars)));
    }

    @Test
    public void testFindVehiclesByAttributesNotFound() throws Exception {

        Mockito.when(service.findByAttributes("AZUL", null))
                .thenThrow(new NoDataFoundException("No vehicles found"));

        mockMvc.perform(get("/vehicles/search")
                .param("color", "AZUL")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("No vehicles found"));
    }

}
