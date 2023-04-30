package br.edu.utfpr.td.tsi.api.service;

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
public class ReportServiceImpl implements ReportService {

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
    public Report findByIdentification(final String identification) {

        Report report = reportRepository.findByIdentification(identification);

        if (report == null) {
            throw new NotFoundException("Report not found");
        }

        return report;

    }

    @Override
    public List<Report> findByAttributes(final Map<String, String> params) {
        return new ArrayList<>(); // TODO implement

    }

    @Override
    public void add(Report report) {

        verifyLicensePlate(report.getVehicle().getRegistration().getLicensePlate());
        
        mapVehicle(report.getVehicle().getId(), report);

        Long addressId = report.getAddress().getId();

        mapAddress(addressId, report);

        reportRepository.save(report);
    }

    @Override
    public Report update(final String identification, Report updatedReport) {

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

        mapVehicle(updatedReport.getVehicle().getId(), updatedReport);
        mapAddress(updatedReport.getAddress().getId(), updatedReport);

        report.setDateOccurrence(updatedReport.getDateOccurrence());
        report.setPeriod(updatedReport.getPeriod());

        reportRepository.save(report);

        return report;
    }

    @Override
    public void delete(final String identification) {

        if (!reportRepository.existsById(identification)) {
            throw new NotFoundException("Report not found");
        }

        reportRepository.deleteById(identification);

    }

    private void mapVehicle(final Long id, Report report) {

        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Vehicle not found"));

        report.setVehicle(vehicle);
    }

    private void mapAddress(final Long id, Report report) {

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Address not found"));

        report.setAddress(address);

    }

    private void verifyLicensePlate(final String licensePlate) {

        Vehicle vehicle = vehicleRepository.findByRegistrationLicensePlate(licensePlate);

        if (vehicle != null) {
            throw new RuntimeException("License plate is already registered");
        }
    }
    
}
