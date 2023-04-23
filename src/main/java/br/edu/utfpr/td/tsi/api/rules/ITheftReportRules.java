package br.edu.utfpr.td.tsi.api.rules;

import br.edu.utfpr.td.tsi.api.exception.InvalidDataException;
import br.edu.utfpr.td.tsi.api.exception.NoDataFoundException;
import br.edu.utfpr.td.tsi.api.exception.TheftReportNotFoundException;
import br.edu.utfpr.td.tsi.api.model.TheftReport;
import java.util.List;
import java.util.Map;

public interface ITheftReportRules {

    List<TheftReport> showAllTheftReports() throws NoDataFoundException;

    TheftReport findTheftReportByIdentification(final String identification) throws TheftReportNotFoundException;

    List<TheftReport> findTheftReportsByAttributes(final Map<String, String> params) throws NoDataFoundException;
    
    void addReport(TheftReport theftReport)throws InvalidDataException;
}
