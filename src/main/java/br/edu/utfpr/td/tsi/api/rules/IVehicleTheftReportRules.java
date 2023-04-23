package br.edu.utfpr.td.tsi.api.rules;

import br.edu.utfpr.td.tsi.api.exception.InvalidDataException;
import br.edu.utfpr.td.tsi.api.exception.NoDataFoundException;
import br.edu.utfpr.td.tsi.api.exception.VehicleTheftReportNotFoundException;
import br.edu.utfpr.td.tsi.api.model.VehicleTheftReport;
import java.util.List;
import java.util.Map;

public interface IVehicleTheftReportRules {

    List<VehicleTheftReport> showAllVehicleTheftReports() throws NoDataFoundException;

    VehicleTheftReport findVehicleTheftReportByIdentification(final String identification) throws VehicleTheftReportNotFoundException;

    List<VehicleTheftReport> findVehicleTheftReportsByAttributes(final Map<String, String> params) throws NoDataFoundException;
    
    void addReport(VehicleTheftReport vehicleTheftReport)throws InvalidDataException;
}
