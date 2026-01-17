package com.rjm.sfk.bank_api.services.controller;

import com.rjm.sfk.bank_api.core.service.ReportService;
import com.rjm.sfk.bank_api.vo.report.ReportVO;
import com.rjm.sfk.bank_api.vo.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * Generates a report for a given client code and date range.
     *
     * @param identification the Identification
     * @param startDate      the start date of the range
     * @param endDate        the end date of the range
     * @return a report VO object with the client code, client name, and a list of account report VO objects
     */
    @GetMapping("/{identification}")
    public ResponseEntity<ApiResponse<Object>> reportsByClientAndRange(
            @PathVariable String identification,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        ReportVO response = reportService.generateReport(
                identification,
                Date.valueOf(startDate),
                Date.valueOf(endDate)
        );

        return ResponseEntity.ok(
                new ApiResponse<>(true,
                        "Reporte de transacciones por cliente",
                        response)
        );
    }

    /**
     * Generates a PDF report for a given client code and date range.
     *
     * @param identification the client code
     * @param startDate      the start date of the range
     * @param endDate        the end date of the range
     * @return a ResponseEntity with OK status and the PDF report as a Base64 string, or
     * NOT_FOUND status if the client is not found, or
     * INTERNAL_SERVER_ERROR status if an error occurs generating the PDF
     */
    @GetMapping("/{identification}/pdf")
    public ResponseEntity<ApiResponse<Object>> reportsByClientAndRangePdf(
            @PathVariable String identification,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        String response = reportService.generatePDF(
                identification,
                Date.valueOf(startDate),
                Date.valueOf(endDate));

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Reporte PDF generado correctamente",
                        response
                )
        );
    }
}
