package com.rjm.sfk.bank_api.vo.report;

import com.rjm.sfk.bank_api.client.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Transaction Report VO.
 *
 * @author javtronic
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionReportVO {

    private Date transactionDate;
    private TransactionType transactionType;
    private BigDecimal amount;
    private BigDecimal balance;

}
