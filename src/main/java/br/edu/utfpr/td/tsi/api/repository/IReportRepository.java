package br.edu.utfpr.td.tsi.api.repository;

import java.util.List;

import br.edu.utfpr.td.tsi.api.model.Report;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReportRepository extends JpaRepository<Report, String> {

    Report findByIdentification(String identification);

    List<Report> findAll(Specification<Report> specification);

}
