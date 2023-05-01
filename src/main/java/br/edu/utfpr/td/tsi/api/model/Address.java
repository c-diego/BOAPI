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

    @NotEmpty(message = "{street.required}")
    @Column(nullable = false)
    private String street;

    @NotNull(message = "{number.required}")
    @Min(value = 1, message = "{numberMin.required}")
    @Column(nullable = false)
    private int number;

    @NotEmpty(message = "{neighborhood.required}")
    @Column(nullable = false)
    private String neighborhood;

    @NotEmpty(message = "{city.required}")
    @Column(nullable = false)
    private String city;
    
    @NotEmpty(message = "{state.required}")
    @Column(nullable = false)
    private String state;

    @JsonIgnore
    @OneToMany(mappedBy = "address", orphanRemoval = true)
    private List<Report> reports;

}
