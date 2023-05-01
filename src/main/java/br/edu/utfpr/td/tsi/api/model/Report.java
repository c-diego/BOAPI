package br.edu.utfpr.td.tsi.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String identification;

    @NotEmpty(message = "{dateOccurrence.required}")
    @Column(nullable = false)
    private String dateOccurrence;

    @NotEmpty(message = "{period.required}")
    @Column(nullable = false)
    private String period;

    @Valid
    @NotNull(message = "{address.required}")
    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;
    
    @JsonIgnoreProperties("reports")
    @Valid
    @NotNull(message = "{vehicle.required}")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private Vehicle vehicle;

}
