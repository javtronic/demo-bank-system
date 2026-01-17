package com.rjm.sfk.bank_api.vo.response;

/**
 * Api Response.
 * @param success
 * @param message
 * @param data
 * @param <T>
 */
public record ApiResponse<T>(
        boolean success,
        String message,
        T data
) {}
