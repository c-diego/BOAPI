package br.edu.utfpr.td.tsi.api.repository;

import java.util.List;

import br.edu.utfpr.td.tsi.api.model.Vehicle;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVehicleRepository extends JpaRepository<Vehicle, Long> {

    Vehicle findByRegistrationLicensePlate(String licensePlate);

    List<Vehicle> findAll(Specification<Vehicle> spec);
}
