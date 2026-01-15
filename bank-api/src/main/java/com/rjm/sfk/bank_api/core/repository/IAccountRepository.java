package com.rjm.sfk.bank_api.core.repository;

import com.rjm.sfk.bank_api.client.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface Account repository.
 *
 * @author javtronic
 */
public interface IAccountRepository extends JpaRepository<AccountEntity, String> {

}
