package com.rjm.sfk.bank_api.services.controller;

import com.rjm.sfk.bank_api.core.service.AccountService;
import com.rjm.sfk.bank_api.vo.AccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Object> createAccount(@RequestBody AccountVO accountVO) {
        accountService.createAccount(accountVO);
        return ResponseEntity.ok().build();
    }

    /**
     * Sets the account status to false for the given account code.
     *
     * @param accountCode the account code
     * @return a ResponseEntity with OK status
     */
    @GetMapping("/inactive")
    public ResponseEntity<Object> inactiveAccount(@RequestParam("accountCode") String accountCode) {
        accountService.inactiveAccount(accountCode);
        return ResponseEntity.ok().build();
    }
}
