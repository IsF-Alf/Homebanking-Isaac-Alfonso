package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.service.CardService;
import com.mindhub.homebanking.service.ClientService;
import com.mindhub.homebanking.utils.CardUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class CardController {
    @Autowired
    private CardService cardService;
    @Autowired
    private ClientService clientService;


    @PostMapping("/clients/current/cards")
    public ResponseEntity<Object> createCard(Authentication authentication, @RequestParam CardType type,
                                             @RequestParam CardColor color)
    {
        Client client = (clientService.findClientByEmail(authentication.getName()));
        if (client == null) {
            throw new UsernameNotFoundException("Unknow client " + authentication.getName());
        }
        if (cardService.existsCardByTypeAndColorAndClientAndActive (type, color, client, true)) {
            return new ResponseEntity<>("You already have the same card", HttpStatus.FORBIDDEN);
        }
        Card card = new Card((client.getFirstName().toUpperCase() + " " + client.getLastName().toUpperCase()), type,
                color, generateNumber(), CardUtils.generateCvv() , LocalDate.now().plusYears(5), LocalDate.now(), true);
        cardService.saveCard(card);
        client.addCard(card);
        clientService.saveClient(client);
        return new ResponseEntity<>("Card created successfully", HttpStatus.CREATED);
    }

    public String generateNumber() {
        String numberGenerator;
        do {
           numberGenerator = CardUtils.generateNumber();
        } while (cardService.existsCardByNumber(numberGenerator));
        return numberGenerator;
    }
    @PatchMapping("/clients/current/cards")
    public ResponseEntity<Object> deleteCardToClient(Authentication authentication, @RequestParam Long id) {
        Client client = clientService.findClientByEmail(authentication.getName());
        Card card = cardService.findById(id);
        if (client == null) {
            return new ResponseEntity<>("Client not found",
                    HttpStatus.FORBIDDEN);
        }
        if (card == null) {
            return new ResponseEntity<>("The card not exist",
                    HttpStatus.FORBIDDEN);
        }
        if (!card.getActive()) {
            return new ResponseEntity<>("The card is inactive",
                    HttpStatus.FORBIDDEN);
        }
        if (!card.getClient().equals(client)) {
            return new ResponseEntity<>("The card not belong to the authenticated client",
                    HttpStatus.FORBIDDEN);
        }
        card.setActive(false);
        cardService.saveCard(card);
        return new ResponseEntity<>("Card deleted successfully", HttpStatus.CREATED);
    }
}