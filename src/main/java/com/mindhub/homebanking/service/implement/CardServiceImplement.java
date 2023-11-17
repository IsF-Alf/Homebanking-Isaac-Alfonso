package com.mindhub.homebanking.service.implement;

import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImplement implements CardService {
    @Autowired
    CardRepository cardRepository;

    @Override
    public Boolean existsCardByTypeAndColorAndClientAndActive(CardType type, CardColor color, Client client, Boolean active) {
        return cardRepository.existsCardByTypeAndColorAndClientAndActive(type, color, client, active);
    }

    @Override
    public void saveCard(Card card) {
        cardRepository.save(card);
    }

    @Override
    public Boolean existsCardByNumber(String number) {
        return cardRepository.existsByNumber(number);
    }
}
