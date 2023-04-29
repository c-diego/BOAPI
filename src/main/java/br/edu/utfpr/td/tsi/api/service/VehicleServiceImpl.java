package br.edu.utfpr.td.tsi.api.service;

import java.util.List;
import java.util.Map;

import br.edu.utfpr.td.tsi.api.exception.NoDataFoundException;
import br.edu.utfpr.td.tsi.api.exception.NotFoundException;
import br.edu.utfpr.td.tsi.api.model.Vehicle;
import br.edu.utfpr.td.tsi.api.repository.IVehicleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private IVehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> showAll() throws NoDataFoundException {

        List<Vehicle> vehicles = vehicleRepository.findAll();

        if (vehicles.isEmpty()) {
            throw new NoDataFoundException("No vehicles found");
        }

        return vehicles;
    }

    @Override
    public Vehicle findByLicensePlate(final String licensePlate) {

        Vehicle vehicle = vehicleRepository.findByRegistrationLicensePlate(licensePlate);

        if (vehicle == null) {
            throw new NotFoundException("Vehicle not found");
        }

        return vehicle;
    }

    @Override
    public List<Vehicle> findByAttributes(final Map<String, String> params) {

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
