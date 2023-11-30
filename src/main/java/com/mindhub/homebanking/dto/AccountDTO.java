package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.AccountType;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class AccountDTO {
    private Long id;
    private String number;
    private LocalDate creationDate;
    private Double balance;
    private List<TransactionDTO> transactions;
    private Boolean active;
    private AccountType accountType;

    public AccountDTO(Account account) {
        id = account.getId();
        number = account.getNumber();
        creationDate = account.getCreationDate();
        balance = account.getBalance();
        transactions = account.getTransaction().stream().map(transaction -> new TransactionDTO(transaction)).collect(
                Collectors.toList());
        active = account.getActive();
        this.accountType = account.getAccountType();
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Double getBalance() {
        return balance;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public Boolean getActive() {
        return active;
    }
    public AccountType getAccountType() {
        return accountType;
    }
}
