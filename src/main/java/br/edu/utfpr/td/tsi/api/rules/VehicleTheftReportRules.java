package br.edu.utfpr.td.tsi.api.rules;

import br.edu.utfpr.td.tsi.api.exception.InvalidDataException;
import br.edu.utfpr.td.tsi.api.exception.NoDataFoundException;
import br.edu.utfpr.td.tsi.api.exception.VehicleTheftReportNotFoundException;
import br.edu.utfpr.td.tsi.api.model.Vehicle;
import br.edu.utfpr.td.tsi.api.model.VehicleRegistration;
import br.edu.utfpr.td.tsi.api.model.VehicleTheftReport;
import br.edu.utfpr.td.tsi.api.repository.IVehicleTheftReportRepository;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VehicleTheftReportRules implements IVehicleTheftReportRules {

    @Autowired
    private IVehicleTheftReportRepository vehicleTheftReportRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<VehicleTheftReport> showAllVehicleTheftReports() throws NoDataFoundException {
        List<VehicleTheftReport> reports = vehicleTheftReportRepository.findAll();

        if (reports.isEmpty()) {
            throw new NoDataFoundException("No vehicles found");
        }

        return reports;
    }

    @Override
    public VehicleTheftReport findVehicleTheftReportByIdentification(String identification) throws VehicleTheftReportNotFoundException {

        VehicleTheftReport theftReport = vehicleTheftReportRepository.findByIdentification(identification);

        if (theftReport == null) {
            throw new VehicleTheftReportNotFoundException("Theft report not found");
        }

        return theftReport;

    }

    @Override
    public List<VehicleTheftReport> findVehicleTheftReportsByAttributes(Map<String, String> params) throws NoDataFoundException {
        return new ArrayList<>();
    }

    @Override
    public void addReport(VehicleTheftReport report) throws InvalidDataException {
        //ValidateVehicleTheftReport.validate(vehicleTheftReport);

        if (report.getVehicle().getId() != null) {
            Vehicle vehicle = entityManager.find(Vehicle.class, report.getVehicle().getId());
            VehicleRegistration registration = entityManager.find(VehicleRegistration.class, report.getVehicle().getRegistration().getId());
            //Address location = entityManager.find(Address.class, report.getLocation().getId());

            vehicle.setRegistration(registration);
            report.setVehicle(vehicle);
           // report.setLocation(location);
        }

        vehicleTheftReportRepository.save(report);
    }

}
