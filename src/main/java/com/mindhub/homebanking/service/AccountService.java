package com.mindhub.homebanking.service;

import com.mindhub.homebanking.dto.AccountDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface AccountService {
    public List<AccountDTO> getAllAccounts();
    public AccountDTO getAccount(Long id);
    public List<AccountDTO> getAll(Authentication authentication);
    public ResponseEntity<Object> createAccount (Authentication authentication);
    public String generateNumber(int min, int max);
}
