package br.edu.utfpr.td.tsi.api.rules;

import br.edu.utfpr.td.tsi.api.exception.InvalidDataException;
import br.edu.utfpr.td.tsi.api.exception.NoDataFoundException;
import br.edu.utfpr.td.tsi.api.exception.TheftReportNotFoundException;
import br.edu.utfpr.td.tsi.api.model.Vehicle;
import br.edu.utfpr.td.tsi.api.model.Registration;
import br.edu.utfpr.td.tsi.api.model.TheftReport;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.edu.utfpr.td.tsi.api.repository.ITheftReportRepository;
import br.edu.utfpr.td.tsi.api.rules.validation.ValidateInputData;

@Component
public class TheftReportRules implements ITheftReportRules {

    @Autowired
    private ITheftReportRepository theftReportRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<TheftReport> showAllTheftReports() throws NoDataFoundException {
        List<TheftReport> reports = theftReportRepository.findAll();

        if (reports.isEmpty()) {
            throw new NoDataFoundException("No vehicles found");
        }

        return reports;
    }

    @Override
    public TheftReport findTheftReportByIdentification(String identification) throws TheftReportNotFoundException {

        TheftReport theftReport = theftReportRepository.findByIdentification(identification);

        if (theftReport == null) {
            throw new TheftReportNotFoundException("Theft report not found");
        }

        return theftReport;

    }

    @Override
    public List<TheftReport> findTheftReportsByAttributes(Map<String, String> params) throws NoDataFoundException {
        return new ArrayList<>();
    }

    @Override
    public void addReport(TheftReport report) throws InvalidDataException {
        
        ValidateInputData.validateTheftReport(report);
        

        if (report.getVehicle().getId() != null) {
            Vehicle vehicle = entityManager.find(Vehicle.class, report.getVehicle().getId());
            Registration registration = entityManager.find(Registration.class, report.getVehicle().getRegistration().getId());
            //Address location = entityManager.find(Address.class, report.getLocation().getId());

            vehicle.setRegistration(registration);
            report.setVehicle(vehicle);
           // report.setLocation(location);
        }

        theftReportRepository.save(report);
    }

}
