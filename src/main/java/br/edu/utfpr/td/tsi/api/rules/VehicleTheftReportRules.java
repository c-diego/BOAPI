package br.edu.utfpr.td.tsi.api.rules;

import br.edu.utfpr.td.tsi.api.exception.NoDataFoundException;
import br.edu.utfpr.td.tsi.api.exception.VehicleNotFoundException;
import br.edu.utfpr.td.tsi.api.model.VehicleTheftReport;
import br.edu.utfpr.td.tsi.api.repository.IVehicleTheftReportRepository;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

public class VehicleTheftReportRules implements IVehicleTheftReportRules {
    
    @Autowired
    private IVehicleTheftReportRepository vehicleTheftReportRepository;

    @Override
    public List<VehicleTheftReport> showAllVehicleTheftReports() throws NoDataFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public VehicleTheftReport findVehicleTheftReportByIdentification(String identification) throws VehicleNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<VehicleTheftReport> findVehicleTheftReportsByAttributes(Map<String, String> params) throws NoDataFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
