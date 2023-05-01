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

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Entity
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotEmpty(message = "{licensePlate.required}")
    @Column(unique = true, nullable = false)
    private String licensePlate;

    @NotEmpty(message = "{state.required}")
    @Column(nullable = false)
    private String state;

    @NotEmpty(message = "{city.required}")
    @Column(nullable = false)
    private String city;

    @JsonIgnore
    @OneToOne(mappedBy = "registration", cascade = CascadeType.ALL)
    private Vehicle vehicle;

}
