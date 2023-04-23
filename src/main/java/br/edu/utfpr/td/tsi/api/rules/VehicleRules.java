package br.edu.utfpr.td.tsi.api.rules;

import br.edu.utfpr.td.tsi.api.exception.NoDataFoundException;
import br.edu.utfpr.td.tsi.api.exception.VehicleNotFoundException;
import br.edu.utfpr.td.tsi.api.model.Vehicle;
import br.edu.utfpr.td.tsi.api.repository.IVehicleRepository;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class VehicleRules implements IVehicleRules {

    @Autowired
    private IVehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> showAllVehicles() throws NoDataFoundException {

        List<Vehicle> vehicles = vehicleRepository.findAll();

        if (vehicles.isEmpty()) {
            throw new NoDataFoundException("No vehicles found");
        }

        return vehicles;
    }

    @Override
    public Vehicle findVehicleByLicensePlate(String licensePlate) throws VehicleNotFoundException {
        Vehicle vehicle = vehicleRepository.findByRegistrationLicensePlate(licensePlate);

        if (vehicle == null) {
            throw new VehicleNotFoundException("Vehicle not found");
        }

        return vehicle;
    }

    @Override
    public List<Vehicle> findVehicleByAttributes(Map<String, String> params) throws NoDataFoundException {

        Specification<Vehicle> spec = Specification.where(null);

        String color = params.get("color");
        String type = params.get("type");

        if (color != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("color"), color));
        }

        if (type != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("type"), type));
        }

        List<Vehicle> vehicles = vehicleRepository.findAll(spec);

        if (vehicles.isEmpty()) {
            throw new NoDataFoundException("No vehicles found");
        }

        return vehicles;

    }

}
