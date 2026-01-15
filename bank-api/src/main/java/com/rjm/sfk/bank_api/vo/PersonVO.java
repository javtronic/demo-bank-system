package com.rjm.sfk.bank_api.vo;

import com.rjm.sfk.bank_api.client.enums.GenderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Person Vo.
 *
 * @author javtronic
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonVO {

    private String personCode;
    private String name;
    private GenderType gender;
    private Integer age;
    private String identification;
    private String phoneNumber;
    private String address;
    private Boolean status;
}
