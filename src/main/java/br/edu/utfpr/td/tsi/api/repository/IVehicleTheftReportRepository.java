package br.edu.utfpr.td.tsi.api.repository;

import br.edu.utfpr.td.tsi.api.model.VehicleTheftReport;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVehicleTheftReportRepository extends JpaRepository<VehicleTheftReport, String> {

    public VehicleTheftReport findByIdentification(String identification);

    public List<VehicleTheftReport> findAll(Specification<VehicleTheftReport> spec);

}
