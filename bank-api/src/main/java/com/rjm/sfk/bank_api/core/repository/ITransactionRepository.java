package com.rjm.sfk.bank_api.core.repository;

import com.rjm.sfk.bank_api.client.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface Transaction repository.
 *
 * @author javtronic
 */
public interface ITransactionRepository extends JpaRepository<TransactionEntity, String> {

}
