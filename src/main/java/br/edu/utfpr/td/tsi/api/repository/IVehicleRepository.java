package br.edu.utfpr.td.tsi.api.repository;

import br.edu.utfpr.td.tsi.api.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVehicleRepository extends JpaRepository<Vehicle, String> {
    
}
