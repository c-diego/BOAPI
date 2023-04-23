package br.edu.utfpr.td.tsi.api.controller;

import br.edu.utfpr.td.tsi.api.model.VehicleTheftReport;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

}
