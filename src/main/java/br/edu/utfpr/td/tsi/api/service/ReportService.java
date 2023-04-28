package br.edu.utfpr.td.tsi.api.service;

import java.util.List;
import java.util.Map;

import br.edu.utfpr.td.tsi.api.exception.NoDataFoundException;
import br.edu.utfpr.td.tsi.api.exception.NotFoundException;
import br.edu.utfpr.td.tsi.api.model.Report;

public interface ReportService {

    Report findByIdentification(final String identification) throws NotFoundException;

    List<Report> showAll() throws NoDataFoundException;

    List<Report> findByAttributes(final Map<String, String> params) throws NoDataFoundException;
    
    void add(Report report);
    
    Report update(final String identification, Report updatedReport) throws NotFoundException;

    void delete(final String identification) throws NotFoundException;
    
}
