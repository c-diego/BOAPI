package br.edu.utfpr.td.tsi.api.service;

import java.util.List;
import java.util.Map;

import br.edu.utfpr.td.tsi.api.model.Report;

public interface ReportService {

    Report add(Report report);

    Report findByIdentification(final String identification);
    
    List<Report> showAll();

    List<Report> findByAttributes(final Map<String, String> params);

    void delete(final String identification);

    Report update(final String identification, Report updatedReport);

}