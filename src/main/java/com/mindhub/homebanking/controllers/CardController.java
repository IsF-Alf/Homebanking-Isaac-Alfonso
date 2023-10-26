package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dto.CardDTO;
import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CardController {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private ClientRepository clientRepository;


    @PostMapping("/clients/current/cards")
    public ResponseEntity<Object> createCard(@RequestParam String type,
                                             @RequestParam String color, Authentication authentication)
    {
        CardType cardType = CardType.valueOf(type.toUpperCase());
        CardColor cardColor = CardColor.valueOf(color.toUpperCase());
        Client client = (clientRepository.findByEmail(authentication.getName()));
        if (client == null) {
            throw new UsernameNotFoundException("Unknow client " + authentication.getName());
        }
        if ((client.getCards().stream().filter(card -> card.getType() == cardType).count() < 3)) {
            Card card = new Card((client.getFirstName() + client.getLastName()), cardType, cardColor,
                    (generateNumber(1, 10000) + " " + generateNumber(1, 10000) + " " + generateNumber(1,
                            10000) + " " + generateNumber(1, 10000)), generateCvv(1, 1000),
                    LocalDate.now().plusYears(5), LocalDate.now());
            cardRepository.save(card);
            client.addCard(card);
            clientRepository.save(client);
            return new ResponseEntity<>("Card created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("You have reached the limit of created cards", HttpStatus.FORBIDDEN);
        }
    }


    private String generateNumber(int min, int max) {
        List<CardDTO> cards = cardRepository.findAll().stream().map(card -> new CardDTO(card)).collect(
                Collectors.toList());
        Set<String> setCards = cards.stream().map(cardDTO -> cardDTO.getNumber()).collect(Collectors.toSet());
        long number;
        String numberCompleted;
        do {
            number = (int) ((Math.random() * (max - min)) + min);
            String formattedNumber = String.format("%04d", number);
            numberCompleted = formattedNumber;
        } while (setCards.contains(numberCompleted));
        return numberCompleted;
    }

    private String generateCvv(int min, int max) {
        List<CardDTO> cards = cardRepository.findAll().stream().map(card -> new CardDTO(card)).collect(
                Collectors.toList());
        Set<String> setCards = cards.stream().map(cardDTO -> cardDTO.getNumber()).collect(Collectors.toSet());
        long number;
        String numberCompleted;
        do {
            number = (int) ((Math.random() * (max - min)) + min);
            String formattedNumber = String.format("%03d", number);
            numberCompleted = formattedNumber;
        } while (setCards.contains(numberCompleted));
        return numberCompleted;
    }
}
