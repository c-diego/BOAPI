package br.edu.utfpr.td.tsi.api.controller;

import java.util.List;

import br.edu.utfpr.td.tsi.api.model.Vehicle;
import br.edu.utfpr.td.tsi.api.service.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(value = "/vehicles", produces = "application/json")
public class VehicleController {

    @Autowired
    private VehicleService service;

    @GetMapping
    public ResponseEntity<List<Vehicle>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{licensePlate}")
    public ResponseEntity<Vehicle> findByLicensePlate(@PathVariable String licensePlate) {
        return ResponseEntity.ok(service.findByLicensePlate(licensePlate));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Vehicle>> findByAttributes(@RequestParam(required = false) String color, @RequestParam(required = false) String type) {
        return ResponseEntity.ok(service.findByAttributes(color, type));
    }

}
