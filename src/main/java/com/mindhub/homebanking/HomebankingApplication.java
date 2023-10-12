package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.mindhub.homebanking.models.TransactionType.CREDIT;
import static com.mindhub.homebanking.models.TransactionType.DEBIT;

@SpringBootApplication
public class HomebankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomebankingApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository) {
        return args -> {
//            --------------------------------------------------CLIENTS------------------------------------------------------------------
            Client isaac = new Client("Isaac", "Alfonso", "ifa24991@gmail.com");
            clientRepository.save(isaac);

            Client melba = new Client("Melba", "Morel", "melba_m@gmail.com");
            clientRepository.save(melba);

//            --------------------------------------------------ACCOUNTS------------------------------------------------------------------
//            # ACCOUNT MELBA
            Account account1Melba = new Account("VIN001", LocalDate.now(), 5000.00);
            Account account2Melva = new Account("VIN002", LocalDate.now().plusDays(1), 7500.00);

//            # ACCOUNT ISAAC
            Account account1Isaac = new Account("IFA001", LocalDate.now(), 8000.00);
            Account account2Isaac = new Account("IFA002", LocalDate.now().plusDays(3), 5000.00);

            melba.addAccount(account1Melba);
            melba.addAccount(account2Melva);
            isaac.addAccount(account1Isaac);
            isaac.addAccount(account2Isaac);

            accountRepository.save(account1Melba);
            accountRepository.save(account2Melva);
            accountRepository.save(account1Isaac);
            accountRepository.save(account2Isaac);

//            --------------------------------------------------TRANSACTIONS------------------------------------------------------------------
//            # TRANSACTIONS MELBA
            Transaction transaction1Melba = new Transaction(DEBIT, -2500.00, "This is a test transaction", LocalDateTime.now());
            account1Melba.addTransaction(transaction1Melba);
            transactionRepository.save(transaction1Melba);

            Transaction transaction2Melba = new Transaction(CREDIT, 1500.00, "This a test transaction 2", LocalDateTime.now());
            account2Melva.addTransaction(transaction2Melba);
            transactionRepository.save(transaction2Melba);

            Transaction transaction3Melba = new Transaction(CREDIT, 1400.00, "This is a test transaction 3", LocalDateTime.now());
            account1Melba.addTransaction(transaction3Melba);
            transactionRepository.save(transaction3Melba);

            Transaction transaction4Melba = new Transaction(CREDIT, 1400.00, "This is a test transaction 4", LocalDateTime.now());
            account1Melba.addTransaction(transaction4Melba);
            transactionRepository.save(transaction4Melba);

//            # TRANSACTIONS ISAAC
            Transaction transaction1Isaac = new Transaction(CREDIT, 1400.00, "This is a test transaction 4", LocalDateTime.now());
            account1Isaac.addTransaction(transaction1Isaac);
            transactionRepository.save(transaction1Isaac);

            Transaction transaction2Isaac = new Transaction(CREDIT, 1400.00, "This is a test transaction 4", LocalDateTime.now());
            account2Isaac.addTransaction(transaction2Isaac);
            transactionRepository.save(transaction2Isaac);

//            --------------------------------------------------PAYMENTS------------------------------------------------------------------

            List<Integer> paymentMortgage = List.of(12, 24, 36, 48, 60);
            List<Integer> paymentAutomotive = List.of(6, 12, 24, 36);
            List<Integer> paymentPersonal = List.of(6, 12, 24);

//            --------------------------------------------------TYPE LOANS------------------------------------------------------------------

            Loan mortgage = new Loan("Mortgage", 500000.00, paymentMortgage);
            loanRepository.save(mortgage);
            Loan automotive = new Loan("Automotive", 300000.00, paymentAutomotive);
            loanRepository.save(automotive);
            Loan personal = new Loan("Personal", 100000.00, paymentPersonal);
            loanRepository.save(personal);

//            --------------------------------------------------CLIENT LOANS------------------------------------------------------------------

            ClientLoan melbaLoan1 = new ClientLoan(400000.00, 60,mortgage);
            ClientLoan melbaLoan2 = new ClientLoan(50000.00, 12,personal);
            ClientLoan isaacLoan1 = new ClientLoan(100000.00, 24,personal);
            ClientLoan isaacLoan2 = new ClientLoan(200000.00, 36,automotive);

            melba.addClientLoan(melbaLoan1);
            mortgage.addClientLoan(melbaLoan1);
            clientLoanRepository.save(melbaLoan1);

            melba.addClientLoan(melbaLoan2);
            personal.addClientLoan(melbaLoan2);
            clientLoanRepository.save(melbaLoan2);

            isaac.addClientLoan(isaacLoan1);
            personal.addClientLoan(isaacLoan1);
            clientLoanRepository.save(isaacLoan1);

            isaac.addClientLoan(isaacLoan2);
            automotive.addClientLoan(isaacLoan2);
            clientLoanRepository.save(isaacLoan2);
        };
    }
}