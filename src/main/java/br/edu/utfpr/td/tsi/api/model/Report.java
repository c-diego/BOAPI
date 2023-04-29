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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Data
@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String identification;

    @NotNull(message = "dateOccurence is required")
    @NotEmpty(message = "dateOccurence is required")
    @Column(nullable = false)
    private String dateOccurrence;

    @NotNull(message = "period is required")
    @NotEmpty(message = "period is required")
    @Column(nullable = false)
    private String period;

    @NotNull(message = "address is required")
    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;
    
    @JsonIgnoreProperties("reports")
    @NotNull(message = "vehicle is required")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "identification")
    private Vehicle vehicle;

}
