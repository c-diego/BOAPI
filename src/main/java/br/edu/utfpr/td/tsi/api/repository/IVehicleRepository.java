package br.edu.utfpr.td.tsi.api.repository;

import java.util.List;

import br.edu.utfpr.td.tsi.api.model.Vehicle;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVehicleRepository extends JpaRepository<Vehicle, Long> {

    Vehicle findByRegistrationLicensePlate(final String licensePlate);

    List<Vehicle> findByColorAndType(final String color, final String type);

    List<Vehicle> findByColor(final String color);

    List<Vehicle> findByType(final String type);

    List<Vehicle> findAll(Specification<Vehicle> spec);

}
