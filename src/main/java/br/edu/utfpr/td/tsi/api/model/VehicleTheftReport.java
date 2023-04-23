package br.edu.utfpr.td.tsi.api.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Entity
public class VehicleTheftReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String identification;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateOfOccurrence;
    
    @Column(nullable = false)
    private String timeOfDay;

    @ManyToOne
    private Address location;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;

    @OneToMany(mappedBy = "theftReport", orphanRemoval = true)
    private List<Part> involvedParts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public Calendar getDateOfOccurrence() {
        return dateOfOccurrence;
    }

    public void setDateOfOccurrence(Calendar dateOfOccurrence) {
        this.dateOfOccurrence = dateOfOccurrence;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public Address getLocation() {
        return location;
    }

    public void setLocation(Address location) {
        this.location = location;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public List<Part> getInvolvedParts() {
        return involvedParts;
    }

    public void setInvolvedParts(List<Part> involvedParts) {
        this.involvedParts = involvedParts;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VehicleTheftReport other = (VehicleTheftReport) obj;
        return Objects.equals(this.id, other.id);
    }

}
