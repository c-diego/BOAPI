package br.edu.utfpr.td.tsi.api.controller;

import br.edu.utfpr.td.tsi.api.model.Vehicle;
import br.edu.utfpr.td.tsi.api.repository.IVehicleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    
    @Autowired
    private IVehicleRepository vehicleRepository;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Vehicle>> vehicles() {
        return ResponseEntity.ok(vehicleRepository.findAll());
    }
    
    @PostMapping
    public Vehicle add(@RequestBody Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

}
