package com.rjm.sfk.bank_api.core.repository;

import com.rjm.sfk.bank_api.client.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface Client repository.
 *
 * @author javtronic
 */
public interface IClientRepository extends JpaRepository<ClientEntity, String> {

}
