package com.rjm.sfk.bank_api.services.controller;

import com.rjm.sfk.bank_api.core.service.ClientService;
import com.rjm.sfk.bank_api.vo.ClientVO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClientService clientService;

    @Test
    void shouldCreateClientSuccessfully() throws Exception {

        String json = """
        {
          "password": "1234",
          "person": {
            "name": "Juan",
            "gender": "MASCULINO",
            "age": 30,
            "identification": "1002003004"
          }
        }
        """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/clients/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Cliente creado correctamente"));
    }

    @Test
    void shouldUpdateClientSuccessfully() throws Exception {

        String json = """
        {
          "clientCode": "6967c5fd-c88c-8328-a4b1-8ca5fca80c79",
          "password": "1234",
          "person": {
            "personCode": "6967c5fd-p3rs-8328-a4b1-8ca5fca80c79",
            "name": "Juan",
            "gender": "MASCULINO",
            "age": 30,
            "identification": "1002003004"
          }
        }
        """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/clients/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Cliente actualizado correctamente"));
    }

    @Test
    void shouldFindClientByCodeSuccessfully() throws Exception {

        String clientCode = "6967c5fd-c88c-8328-a4b1-8ca5fca80c79";

        ClientVO clientVO = new ClientVO();
        clientVO.setClientCode(clientCode);
        clientVO.setClientStatus(true);

        Mockito.when(clientService.findClientByCode(clientCode))
                .thenReturn(clientVO);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/clients/findByCode")
                                .param("clientCode", clientCode)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Cliente encontrado"))
                .andExpect(jsonPath("$.data.clientCode").value(clientCode));
    }

    @Test
    void shouldChangeStatusClientSuccessfully() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/clients/changeStatus")
                        .param("clientCode", "6967c5fd-c88c-8328-a4b1-8ca5fca80c79")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Cambio de estado exitoso"));
    }

    @Test
    void shouldReturnAllClientsSuccessfully() throws Exception {

        ClientVO client1 = new ClientVO();
        client1.setClientCode("111");

        ClientVO client2 = new ClientVO();
        client2.setClientCode("222");

        List<ClientVO> clients = List.of(client1, client2);

        Mockito.when(clientService.findAllClients())
                .thenReturn(clients);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/clients/findAll")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Listado de clientes"))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()").value(2));
    }
}
