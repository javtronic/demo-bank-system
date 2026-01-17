package com.rjm.sfk.bank_api.services.controller;

import com.rjm.sfk.bank_api.core.service.AccountService;
import com.rjm.sfk.bank_api.vo.AccountVO;
import com.rjm.sfk.bank_api.vo.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Account controller.
 *
 * @author javtronic
 */
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    @Lazy
    @Autowired
    private AccountService accountService;

    /**
     * Creates a new account for the given account VO.
     *
     * @param accountVO the account VO
     * @return a ResponseEntity with OK status
     */
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Object>> createAccount(@RequestBody AccountVO accountVO) {
        accountService.createAccount(accountVO);
        return ResponseEntity.ok(
                new ApiResponse<>(true,
                        "Cuenta creada correctamente",
                        null)
        );
    }

    /**
     * Sets the account status to false for the given account code.
     *
     * @param accountCode the account code
     * @return a ResponseEntity with OK status
     */
    @GetMapping("/changeStatus")
    public ResponseEntity<ApiResponse<Object>> changeStatus(@RequestParam("accountCode") String accountCode) {
        accountService.changeStatus(accountCode);
        return ResponseEntity.ok(
                new ApiResponse<>(true,
                        "Cambio de estado exitoso",
                        null)
        );
    }

    /**
     * Finds all accounts.
     *
     * @return a ResponseEntity with OK status and a list of account VO objects as the body
     */
    @GetMapping("/findAll")
    public ResponseEntity<ApiResponse<List<AccountVO>>> findAllAccounts() {
        return ResponseEntity.ok(
                new ApiResponse<>(true,
                        "Listado de cuentas",
                        accountService.findAllAccounts())
        );
    }
}
