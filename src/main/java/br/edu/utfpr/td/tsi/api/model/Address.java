package br.edu.utfpr.td.tsi.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "street is required")
    @NotEmpty(message = "street must not be empty")
    @Column(nullable = false)
    private String street;

    @NotNull(message = "number is required")
    @Min(value = 1, message = "number must be after 0")
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
