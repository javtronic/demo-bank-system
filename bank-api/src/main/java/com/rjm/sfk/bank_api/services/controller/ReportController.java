package com.rjm.sfk.bank_api.services.controller;

import com.rjm.sfk.bank_api.core.service.ReportService;
import com.rjm.sfk.bank_api.vo.report.ReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import jakarta.persistence.EntityNotFoundException;

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
     * @param clientCode the client code
     * @param startDate the start date of the range
     * @param endDate the end date of the range
     * @return a report VO object with the client code, client name, and a list of account report VO objects
     */
    @GetMapping("/{clientCode}")
    public ResponseEntity<ReportVO> reportsByClientAndRange(
            @PathVariable String clientCode,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        ReportVO response = reportService.generateReport(
                clientCode,
                Date.valueOf(startDate),
                Date.valueOf(endDate)
        );

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{clientCode}/pdf")
    public ResponseEntity<String> reportsByClientAndRangePdf(
            @PathVariable String clientCode,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        try {
            String response = reportService.generatePDF(
                    clientCode,
                    Date.valueOf(startDate),
                    Date.valueOf(endDate)
            );
            return ResponseEntity.ok().body(response);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error generating PDF report: " + ex.getMessage());
        }
    }
}
