package br.edu.utfpr.td.tsi.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.List;
import lombok.Data;

@Data
@Entity
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("vehicle")
    private List<TheftReport> theftReports;

}
