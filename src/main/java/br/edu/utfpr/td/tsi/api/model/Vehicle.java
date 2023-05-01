package br.edu.utfpr.td.tsi.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "{yearManufacture.required}")
    @Min(value = 1885, message = "{yearManufactureMin.required}")
    @Column(nullable = false)
    private int yearManufacture;

    @NotEmpty(message = "{color.required}")
    @Column(nullable = false)
    private String color;

    @NotEmpty(message = "{make.required}")
    @Column(nullable = false)
    private String make;

    @NotEmpty(message = "{type.required}")
    @Column(nullable = false)
    private String type;

    @NotEmpty(message = "{model.required}")
    @Column(nullable = false)
    private String model;

    @Valid
    @NotNull(message = "{registration.required}")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Registration registration;

    @JsonIgnoreProperties("vehicle")
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Report> reports;

}
