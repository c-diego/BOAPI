package br.edu.utfpr.td.tsi.api.controller;

import br.edu.utfpr.td.tsi.api.exception.NoDataFoundException;
import br.edu.utfpr.td.tsi.api.exception.NotFoundException;
import br.edu.utfpr.td.tsi.api.model.Address;
import br.edu.utfpr.td.tsi.api.model.Registration;
import br.edu.utfpr.td.tsi.api.model.Report;
import br.edu.utfpr.td.tsi.api.model.Vehicle;
import br.edu.utfpr.td.tsi.api.service.ReportService;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReportController.class)
public class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportService service;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Report> reports;

    @BeforeEach
    public void init() {

        reports = List.of(
                Report.build("report-1", "01/01/2023", "MANHA", null, null),
                Report.build("report-2", "02/02/2023", "TARDE", null, null),
                Report.build("report-3", "03/03/2023", "NOITE", null, null),
                Report.build("report-4", "04/04/2023", "MADRUGADA", null, null)
        );

    }

    @Test
    public void testFindReportNotFound() throws Exception {

        Mockito.when(service.findByIdentification("report-50"))
                .thenThrow(new NotFoundException("Report not found"));

        mockMvc.perform(get("/reports/report-50")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("Report not found"));
    }

    @Test
    public void testFindReportFound() throws Exception {
        Mockito.when(service.findByIdentification("report-1"))
                .thenReturn(reports.get(0));

        mockMvc.perform(get("/reports/report-1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(reports.get(0))));
    }

    @Test
    public void testFindAllReportFound() throws Exception {
        Mockito.when(service.showAll())
                .thenReturn(reports);

        mockMvc.perform(get("/reports")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(reports)));
    }

    @Test
    public void testFindAllReportNotFound() throws Exception {
        Mockito.when(service.showAll())
                .thenThrow(new NoDataFoundException("No reports found"));

        mockMvc.perform(get("/reports")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("No reports found"));
    }

    @Test
    public void testCreateReportSuccessfully() throws Exception {

        Report report = createValidReportWhitoutId();

        String expectedJson = objectMapper.writeValueAsString(report).
                replace("null", "\"report-5\"");

        Mockito.when(service.add(report))
                .then((invocation) -> {
                    Report reportSaved = invocation.getArgument(0, Report.class);
                    reportSaved.setIdentification("report-5");
                    return reportSaved;
                });

        mockMvc.perform(post("/reports")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(report)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void testCreateReportUnsuccessfully() throws Exception {

        Report report = Report.build(null, null, null, null, null);

        mockMvc.perform(post("/reports")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(report)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.dateOccurrence").value("dateOccurence is required"))
                .andExpect(jsonPath("$.period").value("period is required"))
                .andExpect(jsonPath("$.address").value("address is required"))
                .andExpect(jsonPath("$.vehicle").value("vehicle is required"));
    }

    @Test
    public void testUpdateReportSuccessfully() throws Exception {
        Report report = createValidReportWhitoutId();
        report.setIdentification("report-2");

        Mockito.when(service.update(report.getIdentification(), report))
                .thenReturn(report);

        mockMvc.perform(put("/reports/report-2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(report)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(report)));
    }

    @Test
    public void testUpdateReportUnsuccessfully() throws Exception {
        Report report = reports.get(1);
        report.setPeriod(null);

        mockMvc.perform(put("/reports/report-2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(report)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.period").value("period is required"));
    }

    private Report createValidReportWhitoutId() {

        Report report = Report.build(null, "04/04/2023", "MADRUGADA", null, null);
        Address address = Address.build(1l, "RUA 10", 10, "SÃO JOSÉ", "SÃO PAULO", "SÃO PAULO", null);
        Registration registration = Registration.build(1l, "ABC1D23", "SÃO PAULO", "SÃO PAULO", null);
        Vehicle vehicle = Vehicle.build(1l, 0, "BLACK", "FIAT", "CARRO", "UNO", registration, null);

        vehicle.setRegistration(registration);
        report.setVehicle(vehicle);
        report.setAddress(address);

        return report;
    }

}
