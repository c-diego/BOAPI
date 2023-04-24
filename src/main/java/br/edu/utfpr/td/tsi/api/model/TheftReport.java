package br.edu.utfpr.td.tsi.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class TheftReport {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String identification;

    @Column(nullable = false)
    private String dateOfOccurrence;

    @Column(nullable = false)
    private String period;

    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;

    @JsonIgnoreProperties("theftReports")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;

}
