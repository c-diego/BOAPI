package br.edu.utfpr.td.tsi.api.rules;

import br.edu.utfpr.td.tsi.api.model.Vehicle;
import java.util.List;

public interface IVehicleRules {

    List<Vehicle> showAllVehicles();

    Vehicle findVehicleByLicensePlate(final String licensePlate);

    List<Vehicle> findVehicleByColor(final String color);

    List<Vehicle> findVehicleByType(final String type);
    
    List<String> validate(Vehicle vehicle);
    
    List<String> save(Vehicle vehicle);
    
    boolean delete(Long id);
    
    Vehicle update(Long id, Vehicle vehicle);
    
    Vehicle findById(Long id);

}
