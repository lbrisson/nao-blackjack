package com.blackjack.app;

import com.apps.util.Prompter;
import com.blackjack.model.Dealer;
import com.blackjack.model.Table;

import java.util.Scanner;

public class Game {
    Prompter prompter = new Prompter(new Scanner(System.in));
    private Deck deck;
    private Player player;
    private Dealer dealer;
    private Table table;

    public void initialize() {
        deck = new Deck();
        String playerName = prompter.prompt("Please enter your name: ");
        player = new Player(playerName);
        dealer = new Dealer();
        Table = new Table();
    }

    public void start() {
        //game logic
        dealer.deal();
        if (dealerHand.contains(ACE CARD) || playerHand.contains(ACE CARD)) {
            dealer.blackjackCheck();
        }

    }

    public void tie() {
        System.out.println("PUSH");
        player.setChipValue(player.getChipValue() + table.getPlayersCurrentBet);
        endOfRound();
    }

    public void playerWin() {
        System.out.println("YOU WIN");
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
        System.out.println("DEALER WINS");
        dealer.setWins(getDealerWins() + 1);
        endOfRound();
    }

    private void endOfRound() {
        // clear or reset appropriate variables
        // prompt for redeal, or end game
        String redeal = prompter.prompt("Continue?");
    }
}