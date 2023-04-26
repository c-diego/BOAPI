package br.edu.utfpr.td.tsi.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Data
@Entity
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String identification;

    @Column(unique = true, nullable = false)
    private String licensePlate;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String city;

    @JsonIgnore
    @OneToOne(mappedBy = "registration", cascade = CascadeType.ALL)
    private Vehicle vehicle;

}
