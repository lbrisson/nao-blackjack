package com.blackjack.model;

import java.util.Scanner;

public class Dealer {
    // Attributes
    private int wins;
    private boolean playerBlackjack;
    private Deck deck = new Deck();

    // methods
    public void deal(){
        //add first two cards to player and dealer
        playerCards.add(getNextCard()); // off the top of list
        dealerCards.add(getNextCard());
        playerCards.add(getNextCard());
        dealerCards.add(getNextCard());
    }

    public void blackjackCheck(){
        // check for dealer blackjack
        if (dealerCards.getValue(card[1]) + 2nd card == 21) {
            if (playerCards.getValue()+2nd card == 21) {
                tie();
            }
            else {
                dealerWin();
            }
        }
        else if (playerCards.getValue()+2nd card == 21) {
            player.setBlackjack(true);
            playerWin();
        }
    }

    public Card getNextCard() {
        Card newCard = deck.(remove from top)
        return Card;
    }

//    public void flipCard() {
//        //Dealer flips the card
//        // and hands it self appropriate amount of cards
//        // OPTIONAL IF WE HAVE TIME
//    }

    public void tie() {
        System.out.println("PUSH");
        player.setChipValue(player.getChipValue() + table.getPlayersCurrentBet);
        endOfRound();
    }

    public void playerWin() {
        System.out.println("You win");
        if (player.isBlackjack) {
            player.setChipValue(player.getChipValue() + (table.getPlayersCurrentBet*3));
        }
        else {
            player.setChipValue(player.getChipValue() + (table.getPlayersCurrentBet*2));
        }
        player.setBlackjack(false);
        player.setWins(getPlayerWins() + 1);
        endOfRound();
    }

    public void dealerWin() {
        System.out.println("dealer win");
        dealer.setWins(getDealerWins() + 1);
        endOfRound();
    }

    public void endOfRound() {
        System.out.println("play again?");
        deal();
    }

    // ctor??
    /**
     * LB Comment: hmm, I suggest the following params?:
     * - Deck property
     * -
     * */
    public Dealer() {
    super();
    }

    // getter setters
    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

  //  public boolean isBlackJack() {
   //     return blackJack;
   // }

    public void setBlackJack(boolean blackJack) {
    //    this.blackJack = blackJack;
    }

    //

}