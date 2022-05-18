package com.blackjack.model;

import java.util.*;

public class Deck {
    private final List<Cards> deck = new ArrayList<>();
//

    public Deck() {
    }

    public void createDeck() {
        Cards cards[] = Cards.values();
        int counter = 1;
        while(counter <= 4) {

            for(Cards card : cards) {
                deck.add(card);
            }
            counter++;
        }
        System.out.println("Deck Size: " + deck.size());
    }

    public void shuffleDeck() {
        Collections.shuffle(deck);
        System.out.println(deck);
    }

    public List<Cards> getDeck() {
        return deck;
    }
}
