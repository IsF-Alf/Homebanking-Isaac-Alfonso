package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.service.AccountService;
import com.mindhub.homebanking.service.ClientService;
import com.mindhub.homebanking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private AccountService accountService;

    @Transactional
    @PostMapping("/clients/current/transfers")
    public ResponseEntity<Object> createTransaction(Authentication authentication, @RequestParam double amount,
                                                    @RequestParam String description, @RequestParam String originNumber,
                                                    @RequestParam String destinationNumber)
    {
        Client client = clientService.findClientByEmail(authentication.getName());
        Account accountDebit = accountService.findAccountByNumber(originNumber);
        Account accountCredit = accountService.findAccountByNumber(destinationNumber);
        if (client == null) {
            return new ResponseEntity<>("Unknow client " + authentication.getName(), HttpStatus.UNAUTHORIZED);
        }

        if (accountDebit.getClient() != client) {
            return new ResponseEntity<>("The origin account doesn´t belong to the authenticated client",
                    HttpStatus.FORBIDDEN);
        }
        if (accountDebit == null) {
            return new ResponseEntity<>("The origin account doesn´t exist", HttpStatus.FORBIDDEN);
        }
        if (accountCredit == null) {
            return new ResponseEntity<>("The destination account doesn´t exist", HttpStatus.FORBIDDEN);
        }
        if (accountDebit.getBalance() < amount) {
            return new ResponseEntity<>("Your funds are insufficient", HttpStatus.FORBIDDEN);
        }
        if (amount <= 0) {
            return new ResponseEntity<>("The amount cannot be zero or negative", HttpStatus.FORBIDDEN);
        }
        if (accountDebit.getNumber().equals(accountCredit.getNumber())) {
            return new ResponseEntity<>("the destination account cannot be the same as the origin account",
                    HttpStatus.FORBIDDEN);
        } else {
            Transaction transactionDebit = new Transaction(TransactionType.DEBIT, (-amount),
                    accountDebit.getNumber() + description, LocalDateTime.now(), accountDebit.getBalance() - amount,
                    true);

            Transaction transactionCredit = new Transaction(TransactionType.CREDIT, amount,
                    accountCredit.getNumber() + description, LocalDateTime.now(), accountCredit.getBalance() + amount,
                    true);

            transactionService.saveTransaction(transactionDebit);
            accountDebit.addTransaction(transactionDebit);

            transactionService.saveTransaction(transactionCredit);
            accountCredit.addTransaction(transactionCredit);

            accountDebit.setBalance(accountDebit.getBalance() - amount);
            accountCredit.setBalance(accountCredit.getBalance() + amount);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
}
