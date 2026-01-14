package com.rjm.sfk.bank_api.client.repository;

import com.rjm.sfk.bank_api.client.entity.ClientEntity;
import com.rjm.sfk.bank_api.client.repository.configuration.IConfigJPAPersistence;

public interface IClientRepository extends IConfigJPAPersistence<ClientEntity> {

    /**
     * Update client
     * @param client ClientEntity
     */
    void updateClient(ClientEntity client);


}
