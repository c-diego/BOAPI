package br.edu.utfpr.td.tsi.api.service;

import java.util.List;
import java.util.Map;

import br.edu.utfpr.td.tsi.api.model.Vehicle;

public interface VehicleService {

    List<Vehicle> showAll();

    Vehicle findByLicensePlate(final String licensePlate);

    List<Vehicle> findByAttributes(final Map<String, String> params);

}
