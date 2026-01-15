package com.rjm.sfk.bank_api.core.repository;

import com.rjm.sfk.bank_api.client.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface Person repository.
 *
 * @author javtronic
 */
public interface IPersonRepository extends JpaRepository<PersonEntity, String> {

}
