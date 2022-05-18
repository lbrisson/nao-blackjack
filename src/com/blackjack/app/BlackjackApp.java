package com.blackjack.app;

import com.apps.util.Prompter;
import com.blackjack.model.Dealer;
import com.blackjack.model.Deck;
import com.blackjack.model.Player;
import com.blackjack.model.Table;
import com.blackjack.view.TableView;
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
        //prompt player to see if they want to play BlackJack
        promptForPlayBlackJackOrNot();
        start();
    }

    public void start() {
//        showTable();
        while (!gameOver) {
            table.increaseRoundCounter();
            dealer.getDeck();

            promptForBetAmount();

            table.setPotentialEarnings();
            table.dealInitialHands();

            blackjackCheck();

            // At this point blackjackCheck() has been done so neither hands are 21 or busted.
            // this phase only deals with the playerHand (hit or stand)
            // prompt player to hit or stand, since the first two cards will never result in bust, you can always ask hit or stand here
            // if else for player response, hit or stand.
            // if hit, add one card to playerHand and getHandValue,
            //      if >21 call playerLosesHand(), if less restart phase.
            // if stand, it goes to the next phase.
            promptForHitOrStand();
            if (gameOver) {
                break;
            }

            // Next phase is dealing with the dealerHand
            // this part is automatic, we don't need prompts
            // Three outcomes:
            //      dealerHandValue is <17, add card to dealerHand and restart phase
            //      dealerHandValue is >=17 && <=21, go to next phase: comparing dealer and player hands.
            //      dealerHandValue is >21, dealer bust go to playerWinsHand().
            // it should update the scoreboard as it is receiving cards
            while (table.checkDealerHandValue() < 17) {
                table.addToDealerCards();
                System.out.println(table.getDealerCards());
            }

            if (table.checkDealerHandValue() > 21) {
                System.out.println("DEALER BUST");
                table.playerWinsRound();
                promptForEndGameOrContinue();
            }

            // Next phase is for comparing dealer and player hand int values
            //      if playerHandValue==DealerHandValue, tie() ending method called
            //      if else playerHandValue>DealerHandValue, playerWinsHand() ending
            //      else, playerLosesHand() since it is the only option left.
            if (table.checkPlayerHandValue() == table.checkDealerHandValue()){
                table.playerTied();
                promptForEndGameOrContinue();
            }
            else if(table.checkPlayerHandValue() > table.checkDealerHandValue()) {
                table.playerWinsRound();
                promptForEndGameOrContinue();
            }
            else {
                table.playerLosesRound();
                promptForEndGameOrContinue();
            }
        }

    }


    private String promptForPlayerName() {
        String playerName = prompter.prompt("Please enter your name: ");
        System.out.println("Your name is: " + playerName);
        return playerName;
    }


    private void showNoGamesPlayedResults() {
        System.out.println("HAVE A GOOD DAY WITHOUT BLACKJACK");

    }

    private void promptForPlayBlackJackOrNot() {
        String redealOrEndGame = prompter.prompt("Enter (Y)es to continue playing or (N)o to end the Game: ");
        if(redealOrEndGame.trim().toLowerCase().equals("y")) {
            System.out.println("let's continue");
        }
        else if(redealOrEndGame.trim().toLowerCase().equals("n")) {
            gameOver = true;
            showNoGamesPlayedResults();
        }
        else {
            System.out.println("ERROR: Not a valid answer.");
            promptForPlayBlackJackOrNot();
        }
    }


    private void promptForBetAmount() {
        int chips = player.getChipValue();
        String bet = prompter.prompt("How much would you like to bet? Please choose a number between 5 and " + chips + ": ");
        int convertedBet = Integer.parseInt(bet.trim());
        if (convertedBet < 5 || convertedBet > chips) {
            System.out.println("ERROR: you did not enter a number in the valid range 5 to " + chips);
            promptForBetAmount();
        }
        else {
            System.out.println("You have bet: " + convertedBet + " chip(s)");
            player.placeBet(convertedBet);
        }
    }

    private void blackjackCheck() {
        // check for dealer blackjack
        if (table.checkDealerHandValue() == 21) {
            if (table.checkPlayerHandValue() == 21) {
                table.playerTied();
            } else {
                table.playerLosesRound();
            }
            promptForEndGameOrContinue();
        } else if (table.checkPlayerHandValue() == 21) {
            player.setBlackjack(true);
            table.setPotentialEarnings();
            table.playerWinsRound();
            promptForEndGameOrContinue();
        }

    }


    private void promptForHitOrStand() {
        System.out.println(table.getPlayerCards());
        String hitOrStand = prompter.prompt("Enter [h] to HIT or [s] to STAND: ");
        if (hitOrStand.trim().toLowerCase().equals("h")) {
            table.playerHits();
            if (table.checkPlayerHandValue() > 21 ) {
                System.out.println("PLAYER BUST");
                table.playerLosesRound();
                promptForEndGameOrContinue();
            }
            else {
                promptForHitOrStand();
            }
        }
        else if (hitOrStand.trim().toLowerCase().equals("s")){
            System.out.println("continuing to dealer");
        }
        else {
            System.out.println("ERROR: Please enter a valid response.");
            promptForHitOrStand();
        }
    }

    private void promptForEndGameOrContinue() {
        //clear or reset appropriate variables
        //prompt for redeal, or end game
        table.clearActiveCards();
        table.clearPotentialEarnings();
        String redealOrEndGame = prompter.prompt("Enter [y]es to continue playing or [n]o to end the Game: ");
        if (redealOrEndGame.trim().toLowerCase().equals("n")) {
            setGameOver(true);
            showEndOfGameResults();
        }
        else if (redealOrEndGame.trim().toLowerCase().equals("y")) {
            System.out.println("Let's start the next hand!");
            start();
        }
        else {
            System.out.println("ERROR: please enter a valid response");
            promptForEndGameOrContinue();
        }
        System.out.println("BYE");
    }


    private void showEndOfGameResults() {
        System.out.println("END OF GAME RESULTS HERE");
    }


    private void showTable() {
        TableView view = new TableView(table.getScoreboard());
        view.render();
    }

    //Maybe Make different Shows that are more specific

    private void welcome() {
        System.out.println("\n");
        System.out.println("Welcome to Black Jack!");
        System.out.println("\n");
    }

    //ACCESSOR METHODS


    public Boolean getGameOver() {
        return gameOver;
    }

    public void setGameOver(Boolean gameOver) {
        this.gameOver = gameOver;
    }
}
