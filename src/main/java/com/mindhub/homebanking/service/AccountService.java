package com.mindhub.homebanking.service;

import com.mindhub.homebanking.dto.AccountDTO;
import com.mindhub.homebanking.models.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface AccountService {
    List<Account> findAllAccounts();
    Account findAccountById(Long id);
    void saveAccount(Account account);
    Boolean existsAccountByNumber (String number);
    Account findAccountByNumber (String number);
    Account findById (Long id);
    Boolean existsByActive (Boolean active);
}
