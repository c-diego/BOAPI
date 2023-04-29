package br.edu.utfpr.td.tsi.api.controller;

import java.util.List;
import java.util.Map;

import br.edu.utfpr.td.tsi.api.model.Vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import br.edu.utfpr.td.tsi.api.service.VehicleService;

@RestController
@RequestMapping(value = "/vehicles", produces = "application/json")
public class VehicleController {

    @Autowired
    private VehicleService vehicleRules;

    @GetMapping
    public ResponseEntity<List<Vehicle>> findAll() {
        return ResponseEntity.ok(vehicleRules.findAll());
    }

    @GetMapping("/{licensePlate}")
    public ResponseEntity<Vehicle> findByLicensePlate(@PathVariable String licensePlate) {
        return ResponseEntity.ok(vehicleRules.findByLicensePlate(licensePlate));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Vehicle>> findByAttributes(@RequestParam Map<String, String> params) {
        return ResponseEntity.ok(vehicleRules.findByAttributes(params));
    }

}
