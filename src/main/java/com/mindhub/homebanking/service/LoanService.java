package com.mindhub.homebanking.service;

import com.mindhub.homebanking.models.Loan;

import java.util.List;

public interface LoanService {
    List<Loan> findAllLoans();

    Loan findLoanById(Long id);
    void saveLoan (Loan loan);
    Boolean existsByName (String name);
}
