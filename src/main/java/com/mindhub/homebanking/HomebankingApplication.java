package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.mindhub.homebanking.models.CardColor.*;
import static com.mindhub.homebanking.models.TransactionType.CREDIT;
import static com.mindhub.homebanking.models.TransactionType.DEBIT;

@SpringBootApplication
public class HomebankingApplication {
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(HomebankingApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository,
//                                      TransactionRepository transactionRepository, LoanRepository loanRepository,
//                                      ClientLoanRepository clientLoanRepository, CardRepository cardRepository)
//    {
//        return args -> {
////            --------------------------------------------------CLIENTS------------------------------------------------------------------
//            Client isaac = new Client("Isaac", "Alfonso", "ifa.mindbank@gmail.com", passwordEncoder.encode("isaac1"));
//            clientRepository.save(isaac);
//
//            Client melba = new Client("Melba", "Morel", "melba_m@gmail.com", passwordEncoder.encode("melba1"));
//            clientRepository.save(melba);
//
//            Client jack = new Client("Jack", "Sparrow", "js.mindbank@gmail.com", passwordEncoder.encode("jack1"));
//            clientRepository.save(jack);
//
//
////            --------------------------------------------------ACCOUNTS------------------------------------------------------------------
////            # ACCOUNTS MELBA
//            Account account1Melba = new Account("VIN - 001", LocalDate.now(), 5000.00, true, AccountType.SAVINGS);
//            melba.addAccount(account1Melba);
//            accountRepository.save(account1Melba);
//
//            Account account2Melba = new Account("VIN - 002", LocalDate.now().plusDays(1), 7500.00, true,
//                    AccountType.CURRENT);
//            melba.addAccount(account2Melba);
//            accountRepository.save(account2Melba);
//
////            # ACCOUNTS ISAAC
//            Account account1Isaac = new Account("IFA - 001", LocalDate.now(), 85000.00, true, AccountType.SAVINGS);
//            isaac.addAccount(account1Isaac);
//            accountRepository.save(account1Isaac);
//
//            Account account2Isaac = new Account("IFA - 002", LocalDate.now().plusDays(3), 170000.00, true,
//                    AccountType.CURRENT);
//            isaac.addAccount(account2Isaac);
//            accountRepository.save(account2Isaac);
//
//            //            # ACCOUNTS JACK
//            Account account1Jack = new Account("JSP - 001", LocalDate.now(), 8500.00, true, AccountType.SAVINGS);
//            jack.addAccount(account1Jack);
//            accountRepository.save(account1Jack);
//
//
////            --------------------------------------------------TRANSACTIONS------------------------------------------------------------------
////            # TRANSACTIONS MELBA
//            Transaction transaction1Melba = new Transaction(DEBIT, -2500.00, "This is a test transaction",
//                    LocalDateTime.now(), account1Melba.getBalance(), true);
//            account1Melba.addTransaction(transaction1Melba);
//            transactionRepository.save(transaction1Melba);
//
//            Transaction transaction2Melba = new Transaction(CREDIT, 1500.00, "This a test transaction 2",
//                    LocalDateTime.now(), account2Melba.getBalance(), true);
//            account2Melba.addTransaction(transaction2Melba);
//            transactionRepository.save(transaction2Melba);
//
//            Transaction transaction3Melba = new Transaction(CREDIT, 1400.00, "This is a test transaction 3",
//                    LocalDateTime.now(), account1Melba.getBalance(), true);
//            account1Melba.addTransaction(transaction3Melba);
//            transactionRepository.save(transaction3Melba);
//
//            Transaction transaction4Melba = new Transaction(CREDIT, 1400.00, "This is a test transaction 4",
//                    LocalDateTime.now(), account1Melba.getBalance(), true);
//            account1Melba.addTransaction(transaction4Melba);
//            transactionRepository.save(transaction4Melba);
//
////            # TRANSACTIONS ISAAC
//            Transaction transaction1Isaac = new Transaction(CREDIT, 1400.00, "This is a test transaction 1",
//                    LocalDateTime.now(), account1Jack.getBalance(), true);
//            account1Jack.addTransaction(transaction1Isaac);
//            transactionRepository.save(transaction1Isaac);
//
//            Transaction transaction2Isaac = new Transaction(CREDIT, 1400.00, "This is a test transaction 2",
//                    LocalDateTime.now(), account2Isaac.getBalance(), true);
//            account2Isaac.addTransaction(transaction2Isaac);
//            transactionRepository.save(transaction2Isaac);
//
//
////            --------------------------------------------------PAYMENTS------------------------------------------------------------------
//
//            List<Integer> paymentMortgage = List.of(12, 24, 36, 48, 60);
//            List<Integer> paymentAutomotive = List.of(6, 12, 24, 36);
//            List<Integer> paymentPersonal = List.of(6, 12, 24);
//
////            --------------------------------------------------TYPE LOANS------------------------------------------------------------------
//
//            Loan mortgage = new Loan("Mortgage", 500000.00, paymentMortgage, 0.35);
//            loanRepository.save(mortgage);
//            Loan automotive = new Loan("Automotive", 300000.00, paymentAutomotive, 0.25);
//            loanRepository.save(automotive);
//            Loan personal = new Loan("Personal", 100000.00, paymentPersonal, 0.15);
//            loanRepository.save(personal);
//
////            --------------------------------------------------CLIENT LOANS------------------------------------------------------------------
////            # LOANS MELBA
//            ClientLoan loan1Melba = new ClientLoan(400000.00 + (400000.00 * mortgage.getInterestRate()), 60,
//                    400000.00 + (400000.00 * mortgage.getInterestRate()), 60);
//            melba.addClientLoan(loan1Melba);
//            mortgage.addClientLoan(loan1Melba);
//            clientLoanRepository.save(loan1Melba);
//
//            ClientLoan loan2Melba = new ClientLoan(50000.00 + (50000.00 * personal.getInterestRate()), 12,
//                    50000.00 + (50000.00 * personal.getInterestRate()), 12);
//            melba.addClientLoan(loan2Melba);
//            personal.addClientLoan(loan2Melba);
//            clientLoanRepository.save(loan2Melba);
//
////            # LOANS ISAAC
//            ClientLoan loan1Isaac = new ClientLoan(100000.00 + (100000.00 * personal.getInterestRate()), 24,
//                    100000.00 + (100000.00 * personal.getInterestRate()) - 98000.00, 10);
//            isaac.addClientLoan(loan1Isaac);
//            personal.addClientLoan(loan1Isaac);
//            clientLoanRepository.save(loan1Isaac);
//
//            ClientLoan loan2Isaac = new ClientLoan(200000.00 + (200000.00 * automotive.getInterestRate()), 36,
//                    200000.00 + (200000.00 * automotive.getInterestRate()) - 155000.00, 10);
//            isaac.addClientLoan(loan2Isaac);
//            automotive.addClientLoan(loan2Isaac);
//            clientLoanRepository.save(loan2Isaac);
//
//
//            //            --------------------------------------------------CARDS------------------------------------------------------------------
////            # CARDS MELBA
//            Card melbaCardDebit = new Card("MELBA MOREL", CardType.DEBIT, GOLD, "1111 2222 3333 4444", 777,
//                    LocalDate.now().plusYears(5), LocalDate.now(), true);
//            melba.addCard(melbaCardDebit);
//            cardRepository.save(melbaCardDebit);
//
//            Card melbaCardCredit = new Card("MELBA MOREL", CardType.CREDIT, TITANIUM, "5555 6666 7777 8888", 888,
//                    LocalDate.now().plusYears(5), LocalDate.now(), true);
//            melba.addCard(melbaCardCredit);
//            cardRepository.save(melbaCardCredit);
//
////            # CARDS ISAAC
//            Card isaacCardCredit = new Card("ISAAC ALFONSO", CardType.DEBIT, SILVER, "1234 5678 9101 1213", 111,
//                    LocalDate.now().plusYears(5), LocalDate.now(), true);
//            isaac.addCard(isaacCardCredit);
//            cardRepository.save(isaacCardCredit);
//        };
//    }
}