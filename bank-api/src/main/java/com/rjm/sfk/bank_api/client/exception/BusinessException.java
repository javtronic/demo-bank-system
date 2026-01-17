package com.rjm.sfk.bank_api.client.exception;

/**
 * Business exception.
 *
 * @author javtronic
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

}
