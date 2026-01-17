package com.rjm.sfk.bank_api.vo;

import com.rjm.sfk.bank_api.client.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Account Vo.
 *
 * @author javtronic.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountVO {

    private String accountCode;
    private String clientCode;
    private String accountNumber;
    private AccountType accountType;
    private BigDecimal initialBalance;
    private BigDecimal currentBalance;
    private Boolean accountStatus;

    private ClientVO client;
}
