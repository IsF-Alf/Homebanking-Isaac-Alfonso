package com.mindhub.homebanking.service;

import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface CardService {
    boolean existsCardByTypeAndColorAndClient (CardType type, CardColor color, Client client);
    void saveCard (Card card);
    boolean existsCardByNumber (String number);
}
