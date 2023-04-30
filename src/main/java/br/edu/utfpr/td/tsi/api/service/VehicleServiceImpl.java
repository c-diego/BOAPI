package br.edu.utfpr.td.tsi.api.service;

import java.util.List;

import br.edu.utfpr.td.tsi.api.exception.NoDataFoundException;
import br.edu.utfpr.td.tsi.api.exception.NotFoundException;
import br.edu.utfpr.td.tsi.api.model.Vehicle;
import br.edu.utfpr.td.tsi.api.repository.IVehicleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private IVehicleRepository repository;

    @Override
    public List<Vehicle> findAll() {

        List<Vehicle> vehicles = repository.findAll();
        return findVehiclesOrThrowNoDataFoundException(vehicles, "No vehicles found");
    }

    @Override
    public Vehicle findByLicensePlate(final String licensePlate) {

        Vehicle vehicle = repository.findByRegistrationLicensePlate(licensePlate);
        return findVehicleOrThrowNotFoundException(vehicle, "Vehicle not found");

    }

    @Override
    public List<Vehicle> findByAttributes(final String color, final String type) {

        List<Vehicle> vehicles;

        if (color != null && type != null) {
            vehicles = repository.findByColorAndType(color, type);
            return findVehiclesOrThrowNoDataFoundException(vehicles, "No Vehicles found");
        }

        if (color != null) {
            vehicles = repository.findByColor(color);
            return findVehiclesOrThrowNoDataFoundException(vehicles, "No Vehicles found");
        }

        vehicles = repository.findByType(type);
        return findVehiclesOrThrowNoDataFoundException(vehicles, "No Vehicles found");

    }

    private Vehicle findVehicleOrThrowNotFoundException(Vehicle vehicle, final String message) {
        if (vehicle == null) {
            throw new NotFoundException(message);
        }
        return vehicle;
    }

    private List<Vehicle> findVehiclesOrThrowNoDataFoundException(List<Vehicle> vehicles, String message) {
        if (vehicles == null || vehicles.isEmpty()) {
            throw new NoDataFoundException(message);
        }
        return vehicles;
    }
}
