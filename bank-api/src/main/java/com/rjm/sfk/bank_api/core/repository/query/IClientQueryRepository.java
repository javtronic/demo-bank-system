package com.rjm.sfk.bank_api.core.repository.query;

import com.rjm.sfk.bank_api.vo.ClientVO;

import java.util.List;

/**
 * Interface Client query interface.
 */
public interface IClientQueryRepository {

    /**
     * Finds a client by its code.
     *
     * @param clientCode the client code
     * @return the client VO or null if not found
     */
    ClientVO findClientByCode(String clientCode);

    /**
     * Finds a client by its identification.
     *
     * @param identification the client identification
     * @return the client VO or null if not found
     */
    ClientVO findClientByIdentification(String identification);
    /**
     * Finds all clients.
     *
     * @return a list of client VO objects
     */
    List<ClientVO> findAllClients();
}
