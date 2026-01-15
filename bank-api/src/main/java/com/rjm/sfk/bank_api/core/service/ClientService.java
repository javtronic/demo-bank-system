package com.rjm.sfk.bank_api.core.service;

import com.rjm.sfk.bank_api.client.entity.ClientEntity;
import com.rjm.sfk.bank_api.client.entity.PersonEntity;
import com.rjm.sfk.bank_api.core.repository.IClientRepository;
import com.rjm.sfk.bank_api.core.repository.query.IClientQueryRepository;
import com.rjm.sfk.bank_api.vo.ClientVO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

/**
 * Client service.
 *
 * @author javtronic
 */
@Lazy
@Validated
@Service
public class ClientService {

    @Autowired
    private IClientRepository clientRepository;

    @Autowired
    private IClientQueryRepository clientQueryRepository;

    /**
     * Creates a new client from the given client VO.
     *
     * @param clientVO the client VO
     */
    @Transactional
    public void createClient(ClientVO clientVO) {
        PersonEntity person = new PersonEntity();
        BeanUtils.copyProperties(clientVO.getPerson(), person);
        person.setStatus(true);
        person.setCreatedDate(new Date());
        person.setCreatedByUser("SYSTEM");
        person.setCreatedFromIp("127.0.0.1");

        ClientEntity client = new ClientEntity();
        BeanUtils.copyProperties(clientVO, client);
        client.setPerson(person);
        client.setStatus(true);
        client.setCreatedDate(new Date());
        client.setCreatedByUser("SYSTEM");
        client.setCreatedFromIp("127.0.0.1");
        clientRepository.save(client);
    }


    /**
     * Updates the client with the given client VO.
     *
     * @param clientVO the client VO
     */
    @Transactional
    public void updateClient(ClientVO clientVO) {
        if(clientVO.getClientCode() == null) {
            return;
        }

        ClientEntity client = clientRepository.findById(clientVO.getClientCode())
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        client.setClientStatus(clientVO.getClientStatus());
        client.setPassword(clientVO.getPassword());

        PersonEntity person = client.getPerson();
        BeanUtils.copyProperties(clientVO.getPerson(), person);

        client.setLastModifiedDate(new Date());
        client.setLastModifiedByUser("SYSTEM");
        client.setUpdatedFromIp("127.0.0.1");

        clientRepository.save(client);
    }


    /**
     * Finds a client by its code.
     *
     * @param clientCode the client code
     * @return the client VO or null if not found
     */
    public ClientVO findClientByCode(String clientCode) {
        return clientQueryRepository.findClientByCode(clientCode);
    }

    /**
     * Sets the client status to false for the given client code.
     *
     * @param clientCode the client code
     */
    @Transactional
    public void inactiveClient(String clientCode) {
        ClientEntity client = clientRepository.findById(clientCode)
                .orElse(new ClientEntity());

        client.setClientStatus(false);
        client.setLastModifiedDate(new Date());
        client.setLastModifiedByUser("SYSTEM");
        client.setUpdatedFromIp("127.0.0.1");
    }

}
