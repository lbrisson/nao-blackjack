package com.blackjack.model;


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
        System.out.println("Dealer: PUSH!");
        System.out.println();
    }

    public void playerLoses() {
        System.out.println("Dealer: You lose!");
        System.out.println();
    }

    public void playerWins() {
        System.out.println("Dealer: You win!");
        System.out.println();

    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

}