package br.edu.utfpr.td.tsi.api.controller;

import br.edu.utfpr.td.tsi.api.exception.NoDataFoundException;
import br.edu.utfpr.td.tsi.api.model.Vehicle;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.edu.utfpr.td.tsi.api.rules.IVehicleRules;
import jakarta.persistence.EntityNotFoundException;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(value = "/vehicles", produces = "application/json")
public class VehicleController {

    @Autowired
    private IVehicleRules vehicleRules;

    @GetMapping
    public ResponseEntity<List<Vehicle>> findAll() {

        try {
            List<Vehicle> vehicles = vehicleRules.showAllVehicles();
            return ResponseEntity.ok(vehicles);

        } catch (NoDataFoundException ex) {
            return ResponseEntity.noContent().build();
        }

    }

    @GetMapping("/{licensePlate}")
    public ResponseEntity<Vehicle> findByLicensePlate(@PathVariable String licensePlate) {

        try {
            Vehicle vehicle = vehicleRules.findVehicleByLicensePlate(licensePlate);
            return ResponseEntity.ok(vehicle);

        } catch (EntityNotFoundException ex) {
            return ResponseEntity.noContent().build();
        }

    }

    @GetMapping("/search")
    public ResponseEntity<List<Vehicle>> findByAttributes(@RequestParam Map<String,String> params) {

        try {
            List<Vehicle> vehicles = vehicleRules.findVehicleByAttributes(params);
            return ResponseEntity.ok(vehicles);

        } catch (NoDataFoundException ex) {
            return ResponseEntity.notFound().build();
        }

    }

}
