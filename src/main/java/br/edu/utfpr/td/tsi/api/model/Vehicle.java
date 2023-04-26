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

@Data
@Entity
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String identification;

    @Column(nullable = false)
    private int yearManufacture;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String model;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Registration registration;

    @JsonIgnoreProperties("vehicle")
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Report> reports;

}
