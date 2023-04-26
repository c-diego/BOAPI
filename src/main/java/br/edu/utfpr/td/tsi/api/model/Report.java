package br.edu.utfpr.td.tsi.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Data
@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String identification;

    @Column(nullable = false)
    private String dateOccurrence;

    @Column(nullable = false)
    private String period;

    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;

    @JsonIgnoreProperties("reports")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "identification")
    private Vehicle vehicle;

}
