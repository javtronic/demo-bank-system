package com.rjm.sfk.bank_api.vo;

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
    private String accountType;
    private BigDecimal initialBalance;
    private Boolean accountStatus;
}
