package br.edu.utfpr.td.tsi.api.repository;

import br.edu.utfpr.td.tsi.api.model.TheftReport;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITheftReportRepository extends JpaRepository<TheftReport, String> {

    public TheftReport findByIdentification(String identification);

    public List<TheftReport> findAll(Specification<TheftReport> spec);

}
