package com.blackjack.app;

import com.apps.util.Console;
import com.apps.util.Prompter;
import com.blackjack.model.Dealer;
import com.blackjack.model.Deck;
import com.blackjack.model.Player;
import com.blackjack.model.Table;
import com.blackjack.view.TableView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/*
 *This is a the Controller.
 * It orchestrates the overall flow of the application.
 *It prompts the user for input, and then passes that input into the system ("Model").
 * ALL PROMPTS SHOULD COME FROM HERE
 */
public class BlackjackApp {
    Prompter prompter = new Prompter(new Scanner(System.in));
    Boolean gameOver = false;
    private Player player;
    private Dealer dealer;
    private static final int MINIMUM_BET = 5;
    private Table table;

    public void initialize() {
        welcome();
        String playerName = promptForPlayerName();
        player = new Player(playerName);
        dealer = new Dealer();
        table = new Table(player, dealer);
        promptForStart();
    }

    public void start() {

        while (!gameOver) {
            dealer.getDeck();
            promptForBetAmount();
            table.setPotentialEarnings();
            table.dealInitialHands();
            System.out.println("\nDealer face-up card: [" + table.getDealerCards().get(0) + "]");
            System.out.println("Player hand: " + table.getPlayerCards() + "  hand score: " + table.checkPlayerHandValue() + "\n");

            blackjackCheck();

            if (gameOver) {
                break;
            }

            promptForHitOrStand();
            if (gameOver) {
                break;
            }

            System.out.println("Dealer's hand: " + table.getDealerCards() + "  hand score: " + table.checkDealerHandValue());
            while (table.checkDealerHandValue() < 17) {
                table.addToDealerCards();
            }

            if (table.checkDealerHandValue() > 21) {
                System.out.println("DEALER BUST");
                table.playerWinsRound();
                promptForEndGameOrContinue();
            }

            if (gameOver) {
                break;
            }

            if (table.checkPlayerHandValue() == table.checkDealerHandValue()) {
                table.playerTied();
                promptForEndGameOrContinue();
            } else if (table.checkPlayerHandValue() > table.checkDealerHandValue()) {
                table.playerWinsRound();
                promptForEndGameOrContinue();
            }

            if (gameOver) {
                break;
            }

            table.playerLosesRound();
            promptForEndGameOrContinue();
        }

    }

    private String promptForPlayerName() {
        String playerName = prompter.prompt("Please enter your name: ");
        System.out.println("Thanks " + playerName + "!");
        return playerName;
    }

    private void promptForStart() {
        String start = prompter.prompt("Would you like to play a game of blackjack? [Y]es or [N]o: ");
        if (start.trim().toLowerCase().equals("n")) {
            setGameOver(true);
            showNoGamesPlayedResults();
        } else if (start.trim().toLowerCase().equals("y")) {
            System.out.println("WOOHOO let's START\n");
            start();
        } else {
            System.out.println("ERROR: please enter a valid response");
            promptForStart();
        }
    }

    private void showNoGamesPlayedResults() {
        System.out.println("HAVE A GOOD DAY WITHOUT BLACKJACK");
        System.out.println("NO GAMES WERE PLAYED");
    }

    private void promptForBetAmount() {
        int chips = player.getChipValue();
        String bet = prompter.prompt("How much would you like to bet? Please choose a number between 5 and " + chips + ": ");
        int convertedBet = Integer.parseInt(bet.trim());
        if (convertedBet < 5 || convertedBet > chips) {
            System.out.println("ERROR: you did not enter a number in the valid range 5 to " + chips);
            promptForBetAmount();
        } else {
            player.placeBet(convertedBet);
            System.out.println("You have bet: " + convertedBet + " chips.");
        }
    }

    private void blackjackCheck() {
        if (table.checkDealerHandValue() == 21) {
            System.out.println("Dealer has blackjack.");
            if (table.checkPlayerHandValue() == 21) {
                System.out.println("You also have blackjack.");
                table.playerTied();
            } else {
                System.out.println("You do not have blackjack.");
                table.playerLosesRound();
            }
            promptForEndGameOrContinue();
        } else if (table.checkPlayerHandValue() == 21) {
            System.out.println("YOU GOT BLACKJACK!!!");
            player.setBlackjack(true);
            table.setPotentialEarnings();
            table.playerWinsRound();
            promptForEndGameOrContinue();
        }
    }

    private void promptForHitOrStand() {
        String hitOrStand = prompter.prompt("Enter [h] to HIT or [s] to STAND: ");
        if (hitOrStand.trim().toLowerCase().equals("h")) {
            table.playerHits();
            if (table.checkPlayerHandValue() > 21) {
                System.out.println("PLAYER BUST");
                table.playerLosesRound();
                promptForEndGameOrContinue();
            } else if (table.checkPlayerHandValue() == 21) {
                System.out.println("You got 21!!");
            } else {
                promptForHitOrStand();
            }
        } else if (hitOrStand.trim().toLowerCase().equals("s")) {
            System.out.println("\ncontinuing to dealer...");
            System.out.println();
        } else {
            System.out.println("ERROR: Please enter a valid response.");
            promptForHitOrStand();
        }
    }

    private void promptForEndGameOrContinue() {
        if (player.getChipValue() < 5) {
            setGameOver(true);
            System.out.println("YOU ARE OUT OF CHIPS, GAME OVER.");
            showEndOfGameResults();
        } else {
            String redealOrEndGame = prompter.prompt("Enter [y]es to continue playing or [n]o to end the Game: ");
            if (redealOrEndGame.trim().toLowerCase().equals("n")) {
                setGameOver(true);
                showEndOfGameResults();
            } else if (redealOrEndGame.trim().toLowerCase().equals("y")) {
                System.out.println("\n\nLet's start the next hand!");
                start();
            } else {
                System.out.println("ERROR: please enter a valid response");
                promptForEndGameOrContinue();
            }
        }
    }

    private void showEndOfGameResults() {
        showTable();
    }

    private void showTable() {
        TableView view = new TableView(table.getScoreboard(), player.getChipValue());
        view.render();
    }

    private void welcome() {
        String banner = null;
        if (Files.exists(Path.of("resources/blackjacktest.txt"))) {
            try {
                banner = Files.readString(Path.of("resources/blackjacktest.txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(banner);
        System.out.println("\n");
        System.out.println("Thanks for playing Nao Blackjack!");
        System.out.println("\n");
    }

    public Boolean getGameOver() {
        return gameOver;
    }

    public void setGameOver(Boolean gameOver) {
        this.gameOver = gameOver;
    }
}
