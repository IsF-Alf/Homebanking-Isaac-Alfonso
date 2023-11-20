package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.ClientLoan;

public class ClientLoanDTO {
    private Long id;
    private Long loanId;
    private String name;
    private Integer payments;
    private Double amount;
    private Double currentAmount;
    private Integer currentPayments;

    public ClientLoanDTO(ClientLoan clientLoan) {
        id = clientLoan.getId();
        loanId = clientLoan.getLoan().getId();
        name = clientLoan.getLoan().getName();
        payments = clientLoan.getPayment();
        amount = clientLoan.getAmount();
        currentAmount = clientLoan.getCurrentAmount();
        currentPayments = clientLoan.getCurrentPayments();
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

    public Integer getPayments() {
        return payments;
    }

    public Double getAmount() {
        return amount;
    }

    public Double getCurrentAmount() {
        return currentAmount;
    }

    public Integer getCurrentPayments() {
        return currentPayments;
    }
}
