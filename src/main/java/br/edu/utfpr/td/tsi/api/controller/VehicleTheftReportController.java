package br.edu.utfpr.td.tsi.api.controller;

import br.edu.utfpr.td.tsi.api.model.VehicleTheftReport;
import java.util.List;
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
@RequestMapping(path = "/theftReports", produces = "application/json")
public class VehicleTheftReportController {

    @GetMapping
    public ResponseEntity<List<VehicleTheftReport>> findAll() {
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @RequestMapping(path = "/{identification}")
    public ResponseEntity<List<VehicleTheftReport>> findByIdentification() {
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping(consumes = "application/json")
    public ResponseEntity<VehicleTheftReport> addVehicleTheftReport(@RequestBody VehicleTheftReport report) {
        return ResponseEntity.status(HttpStatus.CREATED).body(report);
    }
    
    @PutMapping(path = "/{identification}", consumes = "application/json")
    public ResponseEntity<VehicleTheftReport> updateVehicleTheftReport(@PathVariable String identification, @RequestBody VehicleTheftReport report) {
        return ResponseEntity.ok().body(report);
    }
    
    @DeleteMapping(path = "/{identification}")
    public ResponseEntity<Void> delete(@PathVariable String identification) {
        return ResponseEntity.ok().build();
    }

}
