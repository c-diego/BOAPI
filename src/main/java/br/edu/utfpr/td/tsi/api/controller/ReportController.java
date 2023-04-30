package br.edu.utfpr.td.tsi.api.controller;

import java.util.List;

import br.edu.utfpr.td.tsi.api.model.Report;
import br.edu.utfpr.td.tsi.api.service.ReportService;

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
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/reports", produces = "application/json")
public class ReportController {

    @Autowired
    private ReportService service;

    @GetMapping
    public ResponseEntity<List<Report>> findAll() {
        return ResponseEntity.ok().body(service.showAll());
    }

    @GetMapping
    @RequestMapping(path = "/{identification}")
    public ResponseEntity<Report> findByIdentification(@PathVariable String identification) {
        return ResponseEntity.ok(service.findByIdentification(identification));
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody @Valid Report report) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.add(report));
    }

    @PutMapping(path = "/{identification}", consumes = "application/json")
    public ResponseEntity<?> update(@PathVariable String identification, @RequestBody @Valid Report updatedReport) {
        return ResponseEntity.ok().body(service.update(identification, updatedReport));
    }

    @DeleteMapping(path = "/{identification}")
    public ResponseEntity<Void> delete(@PathVariable String identification) {
        service.delete(identification);
        return ResponseEntity.ok().build();
    }

}
