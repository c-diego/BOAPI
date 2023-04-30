package br.edu.utfpr.td.tsi.api.controller;

import java.util.List;

import br.edu.utfpr.td.tsi.api.exception.NoDataFoundException;
import br.edu.utfpr.td.tsi.api.exception.NotFoundException;
import br.edu.utfpr.td.tsi.api.model.Vehicle;
import br.edu.utfpr.td.tsi.api.service.VehicleService;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(VehicleController.class)
public class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleService service;

    @Autowired
    private ObjectMapper objectMapper;

    private static List<Vehicle> vehicles;

    @BeforeAll
    public static void createVehicles() {
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

        String licensePlate = "ABC1D23";

        MvcResult result = mockMvc.perform(get("/vehicles/" + licensePlate)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
        assertEquals("{\"error\":\"Vehicle not found\"}", result.getResponse().getContentAsString());
    }

    @Test
    public void testFindVehicleFound() throws Exception {
        String licensePlate = "ABC1D24";
        String expectedJson = objectMapper.writeValueAsString(vehicles.get(0));

        Mockito.when(service.findByLicensePlate(licensePlate))
                .thenReturn(vehicles.get(0));

        MvcResult result = mockMvc.perform(get("/vehicles/" + licensePlate)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        assertEquals(expectedJson, result.getResponse().getContentAsString());
    }

    @Test
    public void testFindAllVehiclesNotFound() throws Exception {

        Mockito.when(service.findAll())
                .thenThrow(new NoDataFoundException("No vehicles found"));

        MvcResult result = mockMvc.perform(get("/vehicles")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
        assertEquals("{\"error\":\"No vehicles found\"}", result.getResponse().getContentAsString());
    }

    @Test
    public void testFindAllVehiclesFound() throws Exception {

        String expectedJson = objectMapper.writeValueAsString(vehicles);

        Mockito.when(service.findAll())
                .thenReturn(vehicles);

        MvcResult result = mockMvc.perform(get("/vehicles")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        assertEquals(MediaType.APPLICATION_JSON_VALUE, result.getResponse().getContentType());
        assertEquals(expectedJson, result.getResponse().getContentAsString());
    }

    @Test
    public void testFindVehiclesByAttributesFound() throws Exception {

        List<Vehicle> cars = List.of(vehicles.get(0), vehicles.get(1));

        String expectedJson = objectMapper.writeValueAsString(cars);

        Mockito.when(service.findByAttributes(null, "CARRO"))
                .thenReturn(cars);

        MvcResult result = mockMvc.perform(get("/vehicles/search")
                .param("type", "CARRO")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        assertEquals(MediaType.APPLICATION_JSON_VALUE, result.getResponse().getContentType());
        assertEquals(expectedJson, result.getResponse().getContentAsString());
    }

    @Test
    public void testFindVehiclesByAttributesNotFound() throws Exception {

        Mockito.when(service.findByAttributes("AZUL", null))
                .thenThrow(new NoDataFoundException("No vehicles found"));

        MvcResult result = mockMvc.perform(get("/vehicles/search")
                .param("color", "AZUL")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
        assertEquals(MediaType.APPLICATION_JSON_VALUE, result.getResponse().getContentType());
        assertEquals("{\"error\":\"No vehicles found\"}", result.getResponse().getContentAsString());
    }

}
