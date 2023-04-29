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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Data
@Entity
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String identification;
    
    @NotNull(message = "licensePlate is required")
    @NotEmpty(message = "licensePlate must not be empty")
    @Column(unique = true, nullable = false)
    private String licensePlate;

    @NotNull(message = "state is required")
    @NotEmpty(message = "state must not be empty")
    @Column(nullable = false)
    private String state;

    @NotNull(message = "city is required")
    @NotEmpty(message = "city must not be empty")
    @Column(nullable = false)
    private String city;

    @JsonIgnore
    @OneToOne(mappedBy = "registration", cascade = CascadeType.ALL)
    private Vehicle vehicle;

}
