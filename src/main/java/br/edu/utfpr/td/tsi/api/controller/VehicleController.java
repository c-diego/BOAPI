package br.edu.utfpr.td.tsi.api.controller;

import br.edu.utfpr.td.tsi.api.model.Vehicle;
import br.edu.utfpr.td.tsi.api.repository.IVehicleRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private IVehicleRepository vehicleRepository;

    @GetMapping
    public ResponseEntity<List<Vehicle>> findAll() {

        List<Vehicle> vehicles = vehicleRepository.findAll();

        if (vehicles.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(vehicles);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> findById(@PathVariable Long id) {

        Optional<Vehicle> vehicle = vehicleRepository.findById(id);

        if (vehicle.isPresent()) {
            return ResponseEntity.ok(vehicle.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Vehicle> create(@RequestBody Vehicle vehicle) {

        if (vehicle.getId() != null) {
            return ResponseEntity.badRequest().build();
        }

        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedVehicle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> update(@PathVariable Long id, @RequestBody Vehicle vehicle) {

        Optional<Vehicle> optional = vehicleRepository.findById(id);

        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Long num = optional.get().getId();

        System.out.println("Id: " + num);
        vehicle.setId(num);
        vehicleRepository.save(vehicle);

        return ResponseEntity.ok(vehicle);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        if (!vehicleRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        vehicleRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
