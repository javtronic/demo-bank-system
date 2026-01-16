package com.rjm.sfk.bank_api.services.controller;

import com.rjm.sfk.bank_api.core.service.ClientService;
import com.rjm.sfk.bank_api.vo.ClientVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Client controller
 *
 * @author javtronic
 */
@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    @Lazy
    @Autowired
    private ClientService clientService;

    /**
     * Creates a new client from the given client VO.
     *
     * @param clientVO the client VO
     * @return a ResponseEntity with OK status and no body
     */
    @PostMapping("/create")
    public ResponseEntity<Object> createClient(@RequestBody ClientVO clientVO) {
        clientService.createClient(clientVO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Updates the client with the given client VO.
     *
     * @param clientVO the client VO
     * @return a ResponseEntity with OK status and no body
     */
    @PostMapping("/update")
    public ResponseEntity<Object> updateClient(@RequestBody ClientVO clientVO) {
        clientService.updateClient(clientVO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * Finds a client by its code.
     *
     * @param clientCode the client code
     * @return a ResponseEntity with OK status and the client VO as the body
     */
    @GetMapping("/findByCode")
    public ResponseEntity<Object> findClientByCode(@RequestParam("clientCode") String clientCode) {
        ClientVO clientVO = clientService.findClientByCode(clientCode);
        return ResponseEntity.ok().body(clientVO);
    }

    /**
     * Sets the client status to false for the given client code.
     *
     * @param clientCode the client code
     * @return a ResponseEntity with OK status and no body
     */
    @GetMapping("/inactive")
    public ResponseEntity<Object> inactiveClient(@RequestParam("clientCode") String clientCode) {
        clientService.inactiveClient(clientCode);
        return ResponseEntity.noContent().build();
    }

    /**
     * Finds all clients.
     *
     * @return a ResponseEntity with OK status and a list of client VO objects as the body
     */
    @GetMapping("/findAll")
    public ResponseEntity<List<ClientVO>> findAll() {
        List<ClientVO> clients = clientService.findAllClients();
        return ResponseEntity.ok().body(clients);
    }
}
