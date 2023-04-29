package br.edu.utfpr.td.tsi.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

import lombok.Data;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Data
@Entity
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String identification;

    @NotNull(message = "yearManufacture is required")
    @Min(value = 1885, message = "yearManufacture must be after 1885")
    @Column(nullable = false)
    private int yearManufacture;

    @NotNull(message = "color is required")
    @NotEmpty(message = "color must not be empty")
    @Column(nullable = false)
    private String color;

    @NotNull(message = "make is required")
    @NotEmpty(message = "make must not be empty")
    @Column(nullable = false)
    private String make;

    @NotNull(message = "type is required")
    @NotEmpty(message = "type must not be empty")
    @Column(nullable = false)
    private String type;

    @NotNull(message = "model is required")
    @NotEmpty(message = "model must not be empty")
    @Column(nullable = false)
    private String model;

    @NotNull(message = "registration is required")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Registration registration;

    @JsonIgnoreProperties("vehicle")
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Report> reports;

}
