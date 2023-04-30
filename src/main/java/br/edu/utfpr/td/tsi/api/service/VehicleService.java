package br.edu.utfpr.td.tsi.api.service;

import java.util.List;

import br.edu.utfpr.td.tsi.api.model.Vehicle;

public interface VehicleService {

    List<Vehicle> findAll();

    Vehicle findByLicensePlate(final String licensePlate);

    List<Vehicle> findByAttributes(final String color, final String type);

}
