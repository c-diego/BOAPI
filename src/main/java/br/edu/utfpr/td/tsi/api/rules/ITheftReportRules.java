package br.edu.utfpr.td.tsi.api.rules;

import br.edu.utfpr.td.tsi.api.exception.NoDataFoundException;
import br.edu.utfpr.td.tsi.api.model.TheftReport;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;

public interface ITheftReportRules {

    List<TheftReport> showAllTheftReports() throws NoDataFoundException;

    TheftReport findTheftReportByIdentification(final String identification) throws EntityNotFoundException;

    List<TheftReport> findTheftReportsByAttributes(final Map<String, String> params) throws NoDataFoundException;
    
    void addReport(final TheftReport theftReport);
    
    TheftReport updateTheftReport(final String identification, TheftReport updatedTheftReport) throws EntityNotFoundException;

    void delete(final String identification) throws EntityNotFoundException;
    
}
