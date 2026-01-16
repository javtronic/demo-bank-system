package com.rjm.sfk.bank_api.services.controller;

import com.rjm.sfk.bank_api.core.service.TransactionService;
import com.rjm.sfk.bank_api.vo.TransactionDetailVO;
import com.rjm.sfk.bank_api.vo.TransactionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * Transaction controller.
 *
 * @author javtronic
 */
@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    @Lazy
    @Autowired
    private TransactionService transactionService;

    /**
     * Registers a new transaction in the database.
     *
     * @param transactionVO the transaction to register
     * @return a ResponseEntity with OK status
     */
    @PostMapping("/register")
    public ResponseEntity<Object> registerTransaction(@RequestBody TransactionVO transactionVO) {
        transactionService.registerTransaction(transactionVO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Finds a list of transactions for the given account code and date range.
     *
     * @param accountCode the account code
     * @param startDate   the start date of the range
     * @param endDate     the end date of the range
     * @return a list of transaction detail VO objects
     */
    @GetMapping("/{accountCode}/movements")
    public ResponseEntity<List<TransactionDetailVO>> transactionsByAccountAndRange(
            @PathVariable String accountCode,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate")   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<TransactionDetailVO> response = transactionService.findTransactionsByAccountAndRange(
                accountCode,
                Date.valueOf(startDate),
                Date.valueOf(endDate)
        );

        return ResponseEntity.ok().body(response);
    }
}
