package br.edu.utfpr.td.tsi.api.repository;

import br.edu.utfpr.td.tsi.api.model.Vehicle;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVehicleRepository extends JpaRepository<Vehicle, Long> {

    public Vehicle findByRegistrationLicensePlate(String licensePlate);

    public List<Vehicle> findAll(Specification<Vehicle> spec);
}