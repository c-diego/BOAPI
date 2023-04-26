package br.edu.utfpr.td.tsi.api.rules;

import java.util.List;
import java.util.Map;

import br.edu.utfpr.td.tsi.api.exception.NoDataFoundException;
import br.edu.utfpr.td.tsi.api.exception.NotFoundException;
import br.edu.utfpr.td.tsi.api.model.Vehicle;

public interface IVehicleRules {

    List<Vehicle> showAll() throws NoDataFoundException;

    Vehicle findByLicensePlate(final String licensePlate) throws NotFoundException;

    List<Vehicle> findByAttributes(final Map<String, String> params) throws NoDataFoundException;

}
