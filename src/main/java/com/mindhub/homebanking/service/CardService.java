package com.mindhub.homebanking.service;

import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Client;
import java.util.List;

public interface CardService {
    Boolean existsCardByTypeAndColorAndClientAndActive (CardType type, CardColor color, Client client, Boolean active);
    void saveCard (Card card);
    Boolean existsCardByNumber (String number);
    Card findById (Long id);
    List<Card> findAll();
}
