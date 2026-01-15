package com.rjm.sfk.bank_api.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Client Vo.
 *
 * @author javtronic
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientVO {

    private String clientCode;
    private String password;
    private Boolean clientStatus;

    private PersonVO person;

}
