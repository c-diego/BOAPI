package br.edu.utfpr.td.tsi.api.rules;

import br.edu.utfpr.td.tsi.api.exception.InvalidDataException;
import br.edu.utfpr.td.tsi.api.exception.NoDataFoundException;
import br.edu.utfpr.td.tsi.api.model.Address;
import br.edu.utfpr.td.tsi.api.model.TheftReport;
import br.edu.utfpr.td.tsi.api.model.Vehicle;
import br.edu.utfpr.td.tsi.api.repository.IAddressRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.edu.utfpr.td.tsi.api.repository.ITheftReportRepository;
import br.edu.utfpr.td.tsi.api.repository.IVehicleRepository;
import br.edu.utfpr.td.tsi.api.rules.validation.ValidateInputData;
import jakarta.persistence.EntityNotFoundException;

@Component
public class TheftReportRules implements ITheftReportRules {

    @Autowired
    private ITheftReportRepository theftReportRepository;

    @Autowired
    private IVehicleRepository vehicleRepository;

    @Autowired
    private IAddressRepository addressRepository;

    @Override
    public List<TheftReport> showAllTheftReports() throws NoDataFoundException {
        List<TheftReport> reports = theftReportRepository.findAll();

        if (reports.isEmpty()) {
            throw new NoDataFoundException("No vehicles found");
        }

        return reports;
    }

    @Override
    public TheftReport findTheftReportByIdentification(String identification) throws EntityNotFoundException {

        TheftReport theftReport = theftReportRepository.findByIdentification(identification);

        if (theftReport == null) {
            throw new EntityNotFoundException("Theft report not found");
        }

        return theftReport;

    }

    @Override
    public List<TheftReport> findTheftReportsByAttributes(Map<String, String> params)
            throws NoDataFoundException {

        return new ArrayList<>();
    }

    @Override
    public void addReport(TheftReport report) throws InvalidDataException {

        ValidateInputData.validateTheftReport(report);

        String vehicleIdentification = report.getVehicle().getIdentification();

        if (vehicleIdentification != null) {
            addReportWithExistingVehicle(report.getVehicle().getIdentification(), report);
        
        } else {
            verifyLicensePlate(report.getVehicle().getRegistration().getLicensePlate());
        }

        String addressIdentification = report.getAddress().getIdentification();

        if (addressIdentification != null) {
            addReportWithExistingAddress(addressIdentification, report);
        }

        theftReportRepository.save(report);
    }

    @Override
    public TheftReport updateTheftReport(String identification, TheftReport updatedTheftReport) throws EntityNotFoundException, InvalidDataException {

        TheftReport theftReport = theftReportRepository.findByIdentification(identification);

        if (theftReport == null) {
            throw new EntityNotFoundException("Theft report not found");
        }

        if (updatedTheftReport.getVehicle() != null) {

            String licensePlate = updatedTheftReport.getVehicle().getRegistration().getLicensePlate();
            Vehicle vehicle = vehicleRepository.findByRegistrationLicensePlate(licensePlate);

            if (vehicle == null) {
                throw new EntityNotFoundException("Vehicle not found");
            }

            theftReport.setVehicle(vehicle);
        }

        if (updatedTheftReport.getAddress() != null) {
            
            String addressIdentification = updatedTheftReport.getAddress().getIdentification();

            Address address = addressRepository.findById(addressIdentification).orElse(null);

            if (address == null) {
                throw new EntityNotFoundException("Address not found");
            }

            theftReport.setAddress(address);
        }

        theftReport.setDateOfOccurrence(updatedTheftReport.getDateOfOccurrence());
        theftReport.setPeriod(updatedTheftReport.getPeriod());

        theftReportRepository.save(theftReport);

        return theftReport;
    }

    @Override
    public void delete(String identification) throws EntityNotFoundException {

        if (!theftReportRepository.existsById(identification)) {
            throw new EntityNotFoundException("Theft report not found");
        }

        theftReportRepository.deleteById(identification);

    }

    private void addReportWithExistingVehicle(String identification, TheftReport report) {

        Vehicle vehicle = vehicleRepository.findByIdentification(identification);

        if (vehicle == null) {
            throw new EntityNotFoundException("Vehicle not found");
        }

        report.setVehicle(vehicle);
    }

    private void addReportWithExistingAddress(String identification, TheftReport report) {

        Address address = addressRepository.findByIdentification(identification);

        if (address == null) {
            throw new EntityNotFoundException("Address not found");
        }
        report.setAddress(address);

    }

    private void verifyLicensePlate(String licensePlate) {

        Vehicle vehicle = vehicleRepository.findByRegistrationLicensePlate(licensePlate);

        if (vehicle != null) {
            throw new RuntimeException("License plate is already registered");
        }
    }

}
