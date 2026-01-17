package com.rjm.sfk.bank_api.core.service;

import com.rjm.sfk.bank_api.client.entity.AccountEntity;
import com.rjm.sfk.bank_api.client.entity.TransactionEntity;
import com.rjm.sfk.bank_api.client.enums.TransactionType;
import com.rjm.sfk.bank_api.client.exception.BusinessException;
import com.rjm.sfk.bank_api.core.repository.IAccountRepository;
import com.rjm.sfk.bank_api.core.repository.ITransactionRepository;
import com.rjm.sfk.bank_api.core.repository.query.ITransactionQueryRepository;
import com.rjm.sfk.bank_api.vo.TransactionDetailVO;
import com.rjm.sfk.bank_api.vo.TransactionVO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Lazy
@Service
@Validated
public class TransactionService {

    @Autowired
    private ITransactionRepository transactionRepository;

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private ITransactionQueryRepository transactionQueryRepository;

    /**
     * Registers a new transaction in the database.
     *
     * @param transactionVO the transaction to register
     * @throws RuntimeException if the account does not exist
     */
    @Transactional
    public void registerTransaction(TransactionVO transactionVO) {
        AccountEntity account = accountRepository.findById(transactionVO.getAccountCode())
                .orElseThrow(() -> new EntityNotFoundException("Cuenta no existe"));

        BigDecimal newBalance = calculateBalance(account, transactionVO.getTransactionType(),
                transactionVO.getAmount());

        account.setCurrentBalance(newBalance);
        account.setLastModifiedDate(new Date());
        account.setLastModifiedByUser("SYSTEM");
        account.setUpdatedFromIp("127.0.0.1");

        TransactionEntity transaction = new TransactionEntity();
        transaction.setTransactionDate(new Date());
        transaction.setTransactionType(transactionVO.getTransactionType());
        transaction.setAmount(transactionVO.getAmount());
        transaction.setBalance(newBalance);
        transaction.setAccount(account);
        transaction.setStatus(true);
        transaction.setCreatedDate(new Date());
        transaction.setCreatedByUser("SYSTEM");
        transaction.setCreatedFromIp("127.0.0.1");
        transactionRepository.save(transaction);
    }

    /**
     * Finds a list of transactions for the given account code and date range.
     *
     * @param accountCode the account code
     * @param startDate   the start date of the range
     * @param endDate     the end date of the range
     * @return a list of transaction detail VO objects
     */
    public List<TransactionDetailVO> findTransactionsByAccountAndRange(
            String accountCode, Date startDate, Date endDate) {
        return transactionQueryRepository.findTransactionsByAccountAndRange(
                accountCode, startDate, endDate);
    }

    /**
     * Calculates the new balance of an account after a transaction.
     *
     * @param account the account to update
     * @param type    the type of transaction (debit or credit)
     * @param amount  the amount of the transaction
     * @return the new balance of the account
     * @throws IllegalStateException if the account does not have enough balance for a debit transaction
     */
    private BigDecimal calculateBalance(AccountEntity account, TransactionType type,
                                        BigDecimal amount) {
        if (type == TransactionType.DEBITO) {
            if (account.getCurrentBalance().compareTo(amount) < 0) {
                throw new BusinessException("Saldo no disponible o insuficiente");
            }
            return account.getCurrentBalance().subtract(amount);
        }
        return account.getCurrentBalance().add(amount);
    }
}
