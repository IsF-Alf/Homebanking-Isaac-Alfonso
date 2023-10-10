package com.mindhub.homebanking;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.mindhub.homebanking.models.TransactionType.CREDIT;
import static com.mindhub.homebanking.models.TransactionType.DEBIT;

@SpringBootApplication
public class HomebankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomebankingApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository) {
        return args -> {
//            --------------------------------------------------CLIENTS------------------------------------------------------------------
            Client isaac = new Client("Isaac", "Alfonso", "ifa24991@gmail.com");
            clientRepository.save(isaac);

            Client melba = new Client("Melba", "Morel", "melba_m@gmail.com");
            clientRepository.save(melba);

//            --------------------------------------------------ACCOUNTS------------------------------------------------------------------
            Account account1Melba = new Account("VIN001", LocalDate.now(), 5000.00);
            Account account2Melva = new Account("VIN002", LocalDate.now().plusDays(1), 7500.00);
            Account accountIsaac = new Account("IFA001", LocalDate.now(), 8000.00);

            melba.addAccount(account1Melba);
            melba.addAccount(account2Melva);
            isaac.addAccount(accountIsaac);

            accountRepository.save(account1Melba);
            accountRepository.save(account2Melva);
            accountRepository.save(accountIsaac);

//            --------------------------------------------------TRANSACTIONS------------------------------------------------------------------

            Transaction transaction1Melba = new Transaction(DEBIT, -2500.00, "This is a test transaction", LocalDateTime.now());
            account1Melba.addTransaction(transaction1Melba);
            transactionRepository.save(transaction1Melba);

            Transaction transaction2Melva = new Transaction(CREDIT, 1500.00,"This a test transaction 2", LocalDateTime.now());
            account2Melva.addTransaction(transaction2Melva);
            transactionRepository.save(transaction2Melva);

            Transaction transaction3Melba = new Transaction(CREDIT, 1400.00, "This is a test transaction", LocalDateTime.now());
            account1Melba.addTransaction(transaction3Melba);
            transactionRepository.save(transaction3Melba);

            Transaction transaction4Melba = new Transaction(CREDIT, 1400.00, "This is a test transaction", LocalDateTime.now());
            account1Melba.addTransaction(transaction4Melba);
            transactionRepository.save(transaction4Melba);

        };
    }
}