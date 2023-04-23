package br.edu.utfpr.td.tsi.api.rules;

import br.edu.utfpr.td.tsi.api.exception.NoDataFoundException;
import br.edu.utfpr.td.tsi.api.exception.VehicleNotFoundException;
import br.edu.utfpr.td.tsi.api.model.Vehicle;
import java.util.List;
import java.util.Map;

public interface IVehicleRules {

    List<Vehicle> showAllVehicles() throws NoDataFoundException;

    Vehicle findVehicleByLicensePlate(final String licensePlate) throws VehicleNotFoundException;

    List<Vehicle> findVehicleByAttributes(final Map<String, String> params) throws NoDataFoundException;

}
