package com.mindhub.homebanking.service;

import com.mindhub.homebanking.dto.ClientDTO;
import com.mindhub.homebanking.models.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ClientService {
    List<Client> findAllClients();
    Client findClientById (Long id);
    Client findClientByEmail (String email);
    void saveClient (Client client);
    Boolean existsClientByEmail(String email);
}
