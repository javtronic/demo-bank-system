package com.rjm.sfk.bank_api.core.repository;

import com.rjm.sfk.bank_api.client.entity.ClientEntity;
import com.rjm.sfk.bank_api.client.repository.IClientRepository;
import com.rjm.sfk.bank_api.core.repository.configuration.ConfigJPAPersistence;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import static com.rjm.sfk.bank_api.client.entity.QClientEntity.clientEntity;

/**
 * Client repository.
 *
 * @author javtronic
 */
@Lazy
@Repository
public class ClientRepository extends ConfigJPAPersistence<ClientEntity>
        implements IClientRepository {

    /**
     * Constructor.
     */
    public ClientRepository() {
        super(ClientEntity.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateClient(ClientEntity client) {
        update(clientEntity).where(clientEntity.clientCode.eq(client.getClientCode()))
                .set(clientEntity.password, client.getPassword())
                .set(clientEntity.clientStatus, client.getClientStatus())
                .execute();
    }
}
