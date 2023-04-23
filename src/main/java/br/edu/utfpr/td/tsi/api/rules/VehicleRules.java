package br.edu.utfpr.td.tsi.api.rules;

import br.edu.utfpr.td.tsi.api.model.Vehicle;
import br.edu.utfpr.td.tsi.api.repository.IVehicleRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VehicleRules implements IVehicleRules {

    @Autowired
    private IVehicleRepository vehicleRepository;

    @Override
    public Vehicle findById(Long id) {
        return vehicleRepository.findById(id).orElseGet(null);
    }
    
    @Override
    public List<Vehicle> showAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle findVehicleByLicensePlate(String licensePlate) {
        return vehicleRepository.findByRegistrationLicensePlate(licensePlate);
    }

    @Override
    public List<Vehicle> findVehicleByColor(String color) {
        return vehicleRepository.findByColor(color);
    }

    @Override
    public List<Vehicle> findVehicleByType(String type) {
        return vehicleRepository.findByType(type);
    }

    @Override
    public List<String> validate(Vehicle vehicle) {
        List<String> errors = new ArrayList<>();
    
        if (vehicle.getYearManufacture() < 1885)
            errors.add("YearManufacture is required and must be after 1885");
    
        if (!validateString(vehicle.getColor()))
            errors.add("Color is required");
    
        if (!validateString(vehicle.getMake()))
            errors.add("Make is required");

        if (!validateString(vehicle.getType()))
            errors.add("Type is required");
        
        if (!validateString(vehicle.getModel()))
            errors.add("Model is required");
        
        if (vehicle.getRegistration() == null) 
            errors.add("Registration is required");
        
        if (!validateString(vehicle.getRegistration().getLicensePlate())) 
            errors.add("License plate is required");
        
        if (vehicleRepository.findByRegistrationLicensePlate(vehicle.getRegistration().getLicensePlate()) != null)
            errors.add("License plate must be unique");
        
        if (!validateString(vehicle.getRegistration().getState()))
            errors.add("State is required");
        
        if (!validateString(vehicle.getRegistration().getCity())) 
            errors.add("City is required");
        
        return errors;
        
    }

    @Override
    public List<String> save(Vehicle vehicle) {
        List<String> errors = validate(vehicle);
        
        if (errors.isEmpty())
            vehicleRepository.save(vehicle);
        
        return errors;
    }

    @Override
    public boolean delete(Long id) {
        
        if (!vehicleRepository.existsById(id))
            return false;
        
        vehicleRepository.deleteById(id);
        
        return true;
    }

    @Override
    public Vehicle update(Long id, Vehicle vehicle) {
        
        if (!vehicleRepository.existsById(id))
            return null;
        
        vehicle.setId(id);
        
        return vehicleRepository.save(vehicle);
    }
    
    private boolean validateString(String word) {
        return word != null && !word.isEmpty();
    }
    
}
