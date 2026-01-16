package com.rjm.sfk.bank_api.core.repository;

import com.rjm.sfk.bank_api.client.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interface Account repository.
 *
 * @author javtronic
 */
public interface IAccountRepository extends JpaRepository<AccountEntity, String> {

    /**
     * Checks if an account exists by its account number.
     *
     * @param accountNumber the account number
     * @return true if the account exists, false otherwise
     */
    boolean existsByAccountNumber(String accountNumber);

    /**
     * Finds a list of accounts for the given client code.
     *
     * @param clientCode the client code
     * @return a list of account entities
     */
    List<AccountEntity> findByClient_ClientCode(String clientCode);
}
