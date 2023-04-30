package br.edu.utfpr.td.tsi.api.controller;

import br.edu.utfpr.td.tsi.api.exception.NoDataFoundException;
import br.edu.utfpr.td.tsi.api.exception.NotFoundException;
import br.edu.utfpr.td.tsi.api.model.Report;
import br.edu.utfpr.td.tsi.api.service.ReportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReportController.class)
public class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportService service;

    @Autowired
    private ObjectMapper objectMapper;

    private static List<Report> reports;

    @BeforeAll
    public static void createReports() {

        reports = List.of(
                Report.build("report-1", "01/01/2023", "MANHA", null, null),
                Report.build("report-2", "02/02/2023", "TARDE", null, null),
                Report.build("report-3", "03/03/2023", "NOITE", null, null),
                Report.build("report-4", "04/04/2023", "MADRUGADA", null, null)
        );

    }

    @Test
    public void testFindReportNotFound() throws Exception {
        Mockito.when(service.showAll())
                .thenThrow(new NotFoundException("Report not found"));

        MvcResult result = mockMvc.perform(get("/reports")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
        assertEquals("{\"error\":\"Report not found\"}", result.getResponse().getContentAsString());
    }

    @Test
    public void testFindReportFound() throws Exception {

        String expectedJson = objectMapper.writeValueAsString(reports.get(0));
        String identification = "report-1";

        Mockito.when(service.findByIdentification("report-1"))
                .thenReturn(reports.get(0));

        MvcResult result = mockMvc.perform(get("/reports/" + identification)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        assertEquals(expectedJson, result.getResponse().getContentAsString());
    }

    @Test
    public void testFindAllReportFound() throws Exception {

        String expectedJson = objectMapper.writeValueAsString(reports);

        Mockito.when(service.showAll())
                .thenReturn(reports);

        MvcResult result = mockMvc.perform(get("/reports")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        assertEquals(expectedJson, result.getResponse().getContentAsString());
    }

    @Test
    public void testFindAllReportNotFound() throws Exception {
        Mockito.when(service.showAll())
                .thenThrow(new NoDataFoundException("No reports found"));

        MvcResult result = mockMvc.perform(get("/reports")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
        assertEquals("{\"error\":\"No reports found\"}", result.getResponse().getContentAsString());
    }

    @Test
    public void testCreateReportSuccessfully() throws Exception {

        Report report = Report.build(null, "04/04/2023", "MADRUGADA", null, null);

        Mockito.when(service.add(report))
                .then((invocation) -> {
                    Report reportSaved = invocation.getArgument(0, Report.class);
                    reportSaved.setIdentification("report-5");
                    return report;
                });

        String body = objectMapper.writeValueAsString(report);
        report.setIdentification("report-5");
        String expectedJson = objectMapper.writeValueAsString(report);

        MvcResult result = mockMvc.perform(post("/reports")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
        assertEquals(expectedJson, result.getResponse().getContentAsString());
    }

    @Test
    public void testCreateReportUnsuccessfully() throws Exception {

        Report report = Report.build(null, null, null, null, null);

        // Cria um objeto BindingResult simulando um erro de validação
        BindingResult bindingResult = new BeanPropertyBindingResult(report, "report");
        bindingResult.rejectValue("dateOccurence", "NotNull");
        bindingResult.rejectValue("period", "NotNull");
        bindingResult.rejectValue("address", "NotNull");
        bindingResult.rejectValue("vehicle", "NotNull");

        Mockito.when(service.add(report))
                .thenThrow(new MethodArgumentNotValidException(
                        new MethodParameter(ReportService.class.getMethod("add", Report.class), 0),
                        bindingResult));

        String body = objectMapper.writeValueAsString(report);

        String expectedJson = objectMapper.writeValueAsString(bindingResult);

        MvcResult result = mockMvc.perform(post("/reports")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
        assertEquals(expectedJson, result.getResponse().getContentAsString());
    }

    @Test
    public void testUpdateReportSuccessfully() throws Exception {

    }

    @Test
    public void testUpdateReportUnsuccessfully() throws Exception {

    }

}
