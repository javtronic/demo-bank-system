package com.rjm.sfk.bank_api.vo;

import com.rjm.sfk.bank_api.client.enums.AccountType;
import com.rjm.sfk.bank_api.client.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Transaction Vo.
 *
 * @author javtronic
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetailVO {

    private String transactionCode;
    private Date transactionDate;
    private TransactionType transactionType;
    private BigDecimal amount;
    private BigDecimal balance;
    private String accountCode;
    private String accountNumber;
    private AccountType accountType;
    private BigDecimal initialBalance;
    private Boolean accountStatus;
    private String name;
}
