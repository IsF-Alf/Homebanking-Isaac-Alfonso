package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.CardType;

public class PaymentDTO {
    private String number;
    private int cvv;
    private double amount;
    private String description;
    private CardType typeCard;
    private String email;

    public PaymentDTO() {
    }

    public PaymentDTO(String number, int cvv, Double amount, String description, CardType typeCard, String email) {
        this.number = number;
        this.cvv = cvv;
        this.amount = amount;
        this.description = description;
        this.typeCard = typeCard;
        this.email = email;

    }

    public String getNumber() {
        return number;
    }

    public int getCvv() {
        return cvv;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public CardType getTypeCard() {
        return typeCard;
    }

    public String getEmail() {
        return email;
    }
}
