package br.edu.utfpr.td.tsi.api.controller;

import br.edu.utfpr.td.tsi.api.exception.InvalidDataException;
import br.edu.utfpr.td.tsi.api.exception.NoDataFoundException;
import br.edu.utfpr.td.tsi.api.exception.VehicleTheftReportNotFoundException;
import br.edu.utfpr.td.tsi.api.model.VehicleTheftReport;
import br.edu.utfpr.td.tsi.api.rules.IVehicleTheftReportRules;
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

@RestController
@RequestMapping(path = "/theftReports", produces = "application/json")
public class VehicleTheftReportController {

    @Autowired
    private IVehicleTheftReportRules vehicleTheftReportRules;

    @GetMapping
    public ResponseEntity<List<VehicleTheftReport>> findAll() {

        try {
            List<VehicleTheftReport> reports = vehicleTheftReportRules.showAllVehicleTheftReports();
            return ResponseEntity.ok().body(reports);

        } catch (NoDataFoundException ex) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping
    @RequestMapping(path = "/{identification}")
    public ResponseEntity<VehicleTheftReport> findByIdentification(@PathVariable String identification) {

        try {
            VehicleTheftReport report = vehicleTheftReportRules.findVehicleTheftReportByIdentification(identification);
            return ResponseEntity.ok(report);
        } catch (VehicleTheftReportNotFoundException ex) {

            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> addVehicleTheftReport(@RequestBody VehicleTheftReport report) {

        try {
            vehicleTheftReportRules.addReport(report);
            return ResponseEntity.status(HttpStatus.CREATED).body(report);
        } catch (InvalidDataException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
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
