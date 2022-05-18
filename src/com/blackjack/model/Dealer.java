package com.blackjack.model;

import java.util.Scanner;

public class Dealer {
    // Attributes
    private int wins;
    private Deck deck;

    // ctor
    public Dealer() {
        super();
    }

    //BUSINESS METHODS

    public Deck getDeck() {
        Deck deck = new Deck();
        deck.createDeck();
        deck.shuffleDeck();

        this.deck = deck;

        return deck;
    }

    public Cards getNextCardFromDeck() {
        Cards card = this.deck.getDeck().get(0);
        deck.getDeck().remove(card);

        return card;
    }

    public void playerTied() {
        System.out.println("PUSH");
    }

    public void playerLoses() {
        System.out.println("Dealer: You lose!");
    }

    public void playerWins() {
        System.out.println("Dealer: You win!");

    }

    // getters/setters
    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

}