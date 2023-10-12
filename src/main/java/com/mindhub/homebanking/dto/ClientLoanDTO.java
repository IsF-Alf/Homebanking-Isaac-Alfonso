package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.ClientLoan;

public class ClientLoanDTO {
    private Long id;
    private Long loanId;
    private String name;
    private Integer payment;
    private Double amount;

    public ClientLoanDTO(ClientLoan clientLoan) {
        id = clientLoan.getId();
        loanId =clientLoan.getLoan().getId();
        name = clientLoan.getLoan().getName();
        payment = clientLoan.getPayment();
        amount = clientLoan.getAmount();
    }

    public Long getId() {
        return id;
    }

    public Long getLoanId() {
        return loanId;
    }

    public String getName() {
        return name;
    }

    public Integer getPayment() {
        return payment;
    }

    public Double getAmount() {
        return amount;
    }
}
