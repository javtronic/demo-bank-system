package com.rjm.sfk.bank_api.core.repository;

import com.rjm.sfk.bank_api.client.entity.AccountEntity;
import com.rjm.sfk.bank_api.client.repository.IAccountRepository;
import com.rjm.sfk.bank_api.core.repository.configuration.ConfigJPAPersistence;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 * Account repository.
 *
 * @author javtronic
 */
@Lazy
@Repository
public class AccountRepository extends ConfigJPAPersistence<AccountEntity>
        implements IAccountRepository {

    /**
     * Constructor.
     */
    public AccountRepository() {
        super(AccountEntity.class);
    }
}
