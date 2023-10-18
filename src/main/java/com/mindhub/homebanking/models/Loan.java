package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String name;
    private Double maxAmount;
    @ElementCollection
    private List<Integer> payment;
    @OneToMany(mappedBy = "loan", fetch = FetchType.EAGER)
    private Set<ClientLoan> clientLoans = new HashSet<>();

    public Loan() {
    }

    public Loan(String name, Double maxAmount, List<Integer> payment) {
        this.name = name;
        this.maxAmount = maxAmount;
        this.payment = payment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public List<Integer> getPayment() {
        return payment;
    }

    public void setPayment(List<Integer> payment) {
        this.payment = payment;
    }

    public Long getId() {
        return id;
    }

    public void addClientLoan(ClientLoan cLientLoan) {
        cLientLoan.setLoan(this);
        clientLoans.add(cLientLoan);
    }

    public Set<ClientLoan> getClientLoans() {
        return clientLoans;
    }

    public List<Client> getClients() {
        return clientLoans.stream().map(client -> client.getClient()).collect(Collectors.toList());
    }
}
