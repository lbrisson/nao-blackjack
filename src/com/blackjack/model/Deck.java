package com.blackjack.model;

import java.util.*;

public class Deck {
    Random rnd = new Random();
    private final Collection<Cards> deck = new ArrayList<>();

    Deck() {
    }

    public void createDeck() {
        Cards cards[] = Cards.values();
        int counter = 1;
        while(counter < 4) {

            for(Cards card : cards) {
                deck.add(card);
            }
            counter++;
        }
        System.out.println("Deck Size: " + deck.size());
    }

    public void shuffleDeck() {
        Collections.shuffle((List<?>) deck, new Random(3));
        System.out.println(deck);
    }
}
