package com.rjm.sfk.bank_api.services.configuration;

import com.rjm.sfk.bank_api.client.exception.BusinessException;
import com.rjm.sfk.bank_api.vo.response.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler.
 *
 * @author javtronic
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles BusinessException and returns a ResponseEntity with bad request status and an ApiResponse body containing the exception message.
     *
     * @param ex the BusinessException to handle
     * @return a ResponseEntity with bad request status and an ApiResponse body containing the exception message
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Object>> handleBusiness(BusinessException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ApiResponse<>(
                        false,
                        ex.getMessage(),
                        null
                ));
    }

    /**
     * Handles general exceptions and returns a ResponseEntity with internal server error status and an ApiResponse body containing an error message.
     *
     * @param ex the exception to handle
     * @return a ResponseEntity with internal server error status and an ApiResponse body containing an error message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGeneral(Exception ex) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(
                        false,
                        "Error interno del sistema",
                        null
                ));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Object>> handleDataIntegrity(
            DataIntegrityViolationException ex) {

        String message = "Error de integridad de datos";

        String error = ex.getMostSpecificCause().getMessage();

        if (error != null) {

            if (error.contains("sbatperson_identification_key")) {
                message = "La identificación ya se encuentra registrada";
            }

            // futuros casos
            // if (error.contains("account_number_key")) ...
        }

        return ResponseEntity
                .badRequest()
                .body(new ApiResponse<>(
                        false,
                        message,
                        null
                ));
    }
}
