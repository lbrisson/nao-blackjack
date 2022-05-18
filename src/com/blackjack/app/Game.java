package com.blackjack.app;

//import com.apps.util.Prompter;
import com.blackjack.model.Dealer;
//import com.blackjack.model.Table;
import java.util.Scanner;

/*
This is a lookup table fo student IDs to namers
 */

public class Game {
    //  Prompter prompter = new Prompter(new Scanner(System.in));
    // private Player player;
    private Dealer dealer;
   // private Table table;

    public void initialize() {
        //  String playerName = prompter.prompt("Please enter your name: ");
        //   player = new Player(playerName);
        dealer = new Dealer();
        //  table = new Table(player, dealer);
        start();
    }

    public void start() {
        //  String playerResponse = prompter.prompt("Play or not?: ");

        //  if(playerResponse == YES) {
        //    String playerBet = prompter.prompt("Place bet: ");
        // int convertedBet = Integer.parseInt(playerBet);

        /**
         *What all the method should do:
         *  - check if placedBet is between= MINIMUM_BET(5) - Player.chipValue(how much the player has);
         *  - set value of player.Bet = their placedBet
         *  - subtracts from player chipValue whatever their bet was
         *
         *
         * */
        //player.placeBet();
       // table.dealInitialHands();
    }
    //CHECK FOR BLACKJACK
    //HIT OR STAND

    //HIT

    //  if(playerResponse == NO) {
    //player.leaveGame() ????
    //
    //  }

    //PLAYER DEALER BLACKJACK
    // if (Table.dealerHand.contains(ACE CARD) || Table.Player.playerHand.contains(ACE CARD)) {
    //System.out.println(dealerCards, playerhands);
    //    table.blackjackCheck();
    // }

    //String playerAnswer = promptPlayerHitStand();
    //if(playerAnswer == HIT) {
    // addToPlayerCards()
    //
    //
    // }

    //}

    // public string promptPlayerHitStand() {
    String result  = "";
    //   System.out.println("Hit or Stand?");
    // String playerResponse = prompter.prompt("Hit or Stand?");
    //  result = playerResponse;
    //getPlayerCards();
    //getDealerCards();

    //  return result;

}

// Table.playerWinsRound();
//  MethodToContinue() {
//     promptPlayer();

// }

// private void endOfGAME() {
// clear or reset appropriate variables
// prompt for redeal, or end game
//    String redeal = prompter.prompt("Continue?");
//  }


// while(!gameOver) {

//  }


//}