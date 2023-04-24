package br.edu.utfpr.td.tsi.api.controller;

import br.edu.utfpr.td.tsi.api.exception.NoDataFoundException;
import br.edu.utfpr.td.tsi.api.model.TheftReport;
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
import br.edu.utfpr.td.tsi.api.rules.ITheftReportRules;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping(path = "/theftReports", produces = "application/json")
public class TheftReportController {

    @Autowired
    private ITheftReportRules theftReportRules;

    @GetMapping
    public ResponseEntity<List<TheftReport>> findAll() {

        try {
            List<TheftReport> reports = theftReportRules.showAllTheftReports();
            return ResponseEntity.ok().body(reports);

        } catch (NoDataFoundException ex) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping
    @RequestMapping(path = "/{identification}")
    public ResponseEntity<TheftReport> findByIdentification(@PathVariable String identification) {

        try {
            TheftReport report = theftReportRules.findTheftReportByIdentification(identification);
            return ResponseEntity.ok(report);
        } catch (EntityNotFoundException ex) {

            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> addTheftReport(@RequestBody TheftReport report) {

        try {
            theftReportRules.addReport(report);
            return ResponseEntity.status(HttpStatus.CREATED).body(report);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping(path = "/{identification}", consumes = "application/json")
    public ResponseEntity<?> updateTheftReport(@PathVariable String identification, @RequestBody TheftReport updatedReport) {

        try {
            TheftReport report = theftReportRules.updateTheftReport(identification, updatedReport);
            return ResponseEntity.ok().body(report);
        
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping(path = "/{identification}")
    public ResponseEntity<Void> delete(@PathVariable String identification) {
        
        try {
            theftReportRules.delete(identification);
            return ResponseEntity.ok().build();
        
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
