package com.mindhub.homebanking;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class HomebankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomebankingApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository) {
        return args -> {
            Client client = new Client("Isaac", "Alfonso", "ifa24991@gmail.com");
            clientRepository.save(client);
            Client melba = new Client("Melba", "Morel","melba_m@gmail.com");
            clientRepository.save(melba);
            Account account1 = new Account("VIN001", LocalDate.now(),5000.00);
            Account account2 = new Account("VIN002", LocalDate.now().plusDays(1),7500.00);
            melba.addAccount(account1);
            melba.addAccount(account2);
            accountRepository.save(account1);
            accountRepository.save(account2);
        };
    }
}