package com.blackjack.model;

public class Player {
    //properties
    String name;
    int chipValue = 100;
    int currentBet = 0;
    int wins = 0;
    int bet = 0;
    boolean blackjack = false;

    // ctor
    public Player(String name) {
        this.name = name;
    }

    // business methods
    public void placeBet(int playerBet) {
        this.currentBet = playerBet;
        setChipValue(getChipValue() - playerBet);
    }

    // accessor methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChipValue() {
        return chipValue;
    }

    public void setChipValue(int chipValue) {
        this.chipValue = chipValue;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = this.wins + wins;
    }

    public boolean isBlackjack() {
        return blackjack;
    }

    public void setBlackjack(boolean blackjack) {
        this.blackjack = blackjack;
    }
}