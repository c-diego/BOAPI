package br.edu.utfpr.td.tsi.api.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.edu.utfpr.td.tsi.api.exception.NoDataFoundException;
import br.edu.utfpr.td.tsi.api.exception.NotFoundException;
import br.edu.utfpr.td.tsi.api.model.Address;
import br.edu.utfpr.td.tsi.api.model.Report;
import br.edu.utfpr.td.tsi.api.model.Vehicle;
import br.edu.utfpr.td.tsi.api.repository.IAddressRepository;
import br.edu.utfpr.td.tsi.api.repository.IVehicleRepository;
import br.edu.utfpr.td.tsi.api.repository.IReportRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReportRules implements IReportRules {

    @Autowired
    private IReportRepository reportRepository;

    @Autowired
    private IVehicleRepository vehicleRepository;

    @Autowired
    private IAddressRepository addressRepository;

    @Override
    public List<Report> showAll() throws NoDataFoundException {

        List<Report> reports = reportRepository.findAll();

        if (reports.isEmpty()) {
            throw new NoDataFoundException("No reports found");
        }

        return reports;
    }

    @Override
    public Report findByIdentification(final String identification) throws NotFoundException {

        Report report = reportRepository.findByIdentification(identification);

        if (report == null) {
            throw new NotFoundException("Report not found");
        }

        return report;

    }

    @Override
    public List<Report> findByAttributes(final Map<String, String> params) throws NoDataFoundException {
        return new ArrayList<>(); // TODO implement

    }

    @Override
    public void add(Report report) {

        String vehicleIdentification = report.getVehicle().getIdentification();

        if (vehicleIdentification != null) {
            mapVehicle(report.getVehicle().getIdentification(), report);

        } else {
            verifyLicensePlate(report.getVehicle().getRegistration().getLicensePlate());
        }

        String addressIdentification = report.getAddress().getIdentification();

        if (addressIdentification != null) {
            mapAddress(addressIdentification, report);
        }

        reportRepository.save(report);
    }

    @Override
    public Report update(final String identification, Report updatedReport) throws NotFoundException {

        Report report = reportRepository.findByIdentification(identification);

        if (report == null) {
            throw new NotFoundException("Report not found");
        }

        if (updatedReport.getVehicle() == null) {
            throw new NotFoundException("Vehicle not found");
        }

        if (updatedReport.getAddress() == null) {
            throw new NotFoundException("Address not found");
        }

        mapVehicle(updatedReport.getVehicle().getIdentification(), updatedReport);
        mapAddress(updatedReport.getAddress().getIdentification(), updatedReport);
        

        report.setDateOccurrence(updatedReport.getDateOccurrence());
        report.setPeriod(updatedReport.getPeriod());

        reportRepository.save(report);

        return report;
    }

    @Override
    public void delete(final String identification) throws NotFoundException {

        if (!reportRepository.existsById(identification)) {
            throw new NotFoundException("Report not found");
        }

        reportRepository.deleteById(identification);

    }

    private void mapVehicle(final String identification, Report report) {

        Vehicle vehicle = vehicleRepository.findByIdentification(identification);

        if (vehicle == null) {
            throw new NotFoundException("Vehicle not found");
        }

        report.setVehicle(vehicle);
    }

    private void mapAddress(final String identification, Report report) {

        Address address = addressRepository.findByIdentification(identification);

        if (address == null) {
            throw new NotFoundException("Address not found");
        }
        
        report.setAddress(address);

    }

    private void verifyLicensePlate(final String licensePlate) {

        Vehicle vehicle = vehicleRepository.findByRegistrationLicensePlate(licensePlate);

        if (vehicle != null) {
            throw new RuntimeException("License plate is already registered");
        }
    }

}