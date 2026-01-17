package com.rjm.sfk.bank_api.core.service;

import com.rjm.sfk.bank_api.client.entity.AccountEntity;
import com.rjm.sfk.bank_api.client.entity.ClientEntity;
import com.rjm.sfk.bank_api.client.exception.BusinessException;
import com.rjm.sfk.bank_api.core.repository.IAccountRepository;
import com.rjm.sfk.bank_api.core.repository.IClientRepository;
import com.rjm.sfk.bank_api.core.repository.query.IAccountQueryRepository;
import com.rjm.sfk.bank_api.vo.AccountVO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Account service.
 *
 * @author javtronic
 */
@Lazy
@Validated
@Service
public class AccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IClientRepository clientRepository;

    @Autowired
    private IAccountQueryRepository accountQueryRepository;

    /**
     * Creates a new account for the given client.
     *
     * @param accountVO the account VO
     * @throws IllegalStateException if the account number already exists
     */
    @Transactional
    public void createAccount(AccountVO accountVO) {
        ClientEntity client = clientRepository.findById(accountVO.getClientCode())
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        if (accountRepository.existsByAccountNumber(accountVO.getAccountNumber())) {
            throw new BusinessException("El número de cuenta ya existe");
        }

        AccountEntity account = new AccountEntity();
        BeanUtils.copyProperties(accountVO, account);
        account.setCurrentBalance(account.getInitialBalance());
        account.setClient(client);
        account.setStatus(true);
        account.setCreatedDate(new Date());
        account.setCreatedByUser("SYSTEM");
        account.setCreatedFromIp("127.0.0.1");
        accountRepository.save(account);
    }


    /**
     * Sets the account status to false for the given account code.
     *
     * @param accountCode the account code
     * @throws EntityNotFoundException if the account is not found
     */
    @Transactional
    public void changeStatus(String accountCode) {
        AccountEntity account = accountRepository.findById(accountCode)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        account.setAccountStatus(!account.getAccountStatus());
        account.setLastModifiedDate(new Date());
        account.setLastModifiedByUser("SYSTEM");
        account.setUpdatedFromIp("127.0.0.1");
    }

    /**
     * Finds all accounts.
     *
     * @return a list of account VO objects
     */
    public List<AccountVO> findAllAccounts() {
        return accountQueryRepository.findAllAccounts();
    }
}
