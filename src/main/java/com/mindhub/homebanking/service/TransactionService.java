package com.mindhub.homebanking.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface TransactionService {
    public ResponseEntity<Object> createTransaction(Authentication authentication, double amount, String description,
                                                    String originNumber, String destinationNumber);
}
