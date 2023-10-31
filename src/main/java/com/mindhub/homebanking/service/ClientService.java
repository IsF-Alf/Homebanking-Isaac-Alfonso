package com.mindhub.homebanking.service;

import com.mindhub.homebanking.dto.ClientDTO;
import com.mindhub.homebanking.models.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ClientService {
    void saveClient(Client client);
    public List<ClientDTO> getAllClients();
    public ClientDTO getClient(Long id);
    public ClientDTO getAll(Authentication authentication);
    public ResponseEntity<Object> register(String firstName, String lastName, String email, String password);
    public String generateNumber(int min, int max);
}
