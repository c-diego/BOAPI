package br.edu.utfpr.td.tsi.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String identification;

    @NotNull(message = "street is required")
    @NotEmpty(message = "street must not be empty")
    @Column(nullable = false)
    private String street;

    @Min(value = 1, message = "number is required")
    @Column(nullable = false)
    private int number;

    @NotNull(message = "neighborhood is required")
    @NotEmpty(message = "neighborhood must not be empty")
    @Column(nullable = false)
    private String neighborhood;

    @NotNull(message = "city is required")
    @NotEmpty(message = "city must not be empty")
    @Column(nullable = false)
    private String city;
    
    @NotNull(message = "state is required")
    @NotEmpty(message = "state must not be empty")
    @Column(nullable = false)
    private String state;

    @JsonIgnore
    @OneToMany(mappedBy = "address", orphanRemoval = true)
    private List<Report> reports;

}
