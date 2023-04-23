package br.edu.utfpr.td.tsi.api.controller;

import br.edu.utfpr.td.tsi.api.model.Vehicle;
import java.util.List;
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
import br.edu.utfpr.td.tsi.api.rules.IVehicleRules;

@RestController
@RequestMapping(value = "/vehicles", produces = "application/json")
public class VehicleController {
    
    @Autowired
    private IVehicleRules vehicleRules;

    @GetMapping
    public ResponseEntity<List<Vehicle>> findAll() {

        List<Vehicle> vehicles = vehicleRules.showAllVehicles();

        if (vehicles.isEmpty())
            return ResponseEntity.noContent().build();
     
        return ResponseEntity.ok(vehicles);
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> findById(@PathVariable Long id) {

        Vehicle vehicle = vehicleRules.findById(id);

        if (vehicle == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(vehicle);
            
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> create(@RequestBody Vehicle vehicle) {
        
        List<String> errors = vehicleRules.save(vehicle);
        
        if (!errors.isEmpty())
            return ResponseEntity.badRequest().body(errors);
        
        if (errors.isEmpty())    
            return ResponseEntity.status(HttpStatus.CREATED).body(vehicle);

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<Vehicle> update(@PathVariable Long id, @RequestBody Vehicle vehicle) {

        Vehicle newVehicle = vehicleRules.update(id, vehicle);
        if (newVehicle == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(newVehicle);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        if (vehicleRules.delete(id))
            return ResponseEntity.noContent().build();
        
        return ResponseEntity.notFound().build();
    }
}
