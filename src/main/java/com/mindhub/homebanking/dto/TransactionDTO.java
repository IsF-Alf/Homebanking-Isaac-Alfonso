package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;

import java.time.LocalDateTime;

public class TransactionDTO {
    private Long id;
    private TransactionType type;
    private Double amount;
    private String description;
    private LocalDateTime date;
    private Double currentBalance;
    private Boolean active;

    public TransactionDTO(Transaction transaction) {
        id = transaction.getId();
        type = transaction.getType();
        amount = transaction.getAmount();
        description = transaction.getDescription();
        date = transaction.getDate();
        currentBalance = transaction.getCurrentBalance();
        active = transaction.getActive();
    }

    public Long getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public Double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Double getCurrentBalance() {
        return currentBalance;
    }

    public Boolean getActive() {
        return active;
    }
}
