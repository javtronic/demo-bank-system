package com.rjm.sfk.bank_api.core.repository;

import com.rjm.sfk.bank_api.client.entity.TransactionEntity;
import com.rjm.sfk.bank_api.client.repository.ITransactionRepository;
import com.rjm.sfk.bank_api.core.repository.configuration.ConfigJPAPersistence;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 * Transaction repository.
 *
 * @author javtronic
 */
@Lazy
@Repository
public class TransactionRepository extends ConfigJPAPersistence<TransactionEntity>
        implements ITransactionRepository {

    public TransactionRepository() {
        super(TransactionEntity.class);
    }


}
