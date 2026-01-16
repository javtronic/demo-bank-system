package com.rjm.sfk.bank_api.vo.report;

import com.rjm.sfk.bank_api.client.enums.AccountType;

import java.math.BigDecimal;
import java.util.List;

/**
 * Account Report VO.
 *
 * @param accountNumber
 * @param accountType
 * @param initialBalance
 * @param accountStatus
 * @param transactions
 * @param totalCredits
 * @param totalDebits
 */
public record AccountReportVO(
        String accountNumber,
        AccountType accountType,
        BigDecimal initialBalance,
        Boolean accountStatus,
        List<TransactionReportVO> transactions,
        BigDecimal totalCredits,
        BigDecimal totalDebits
)
{ }
