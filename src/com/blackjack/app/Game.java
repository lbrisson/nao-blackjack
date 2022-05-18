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
         * */
        //player.placeBet();
       // table.dealInitialHands();
    }
    //CHECK FOR BLACKJACK
    //HIT OR STAND

    //HIT

//      if(playerResponse == NO) {
//    player.leaveGame() ????
//
//      }

//    PLAYER DEALER BLACKJACK
//     if (Table.dealerHand.contains(ACE CARD) || Table.Player.playerHand.contains(ACE CARD)) {
//    System.out.println(dealerCards, playerhands);
//        table.blackjackCheck();
//     }

    // At this point blackjackCheck() has been done so neither hands are 21 or busted.
    // this phase only deals with the playerHand (hit or stand)
    // prompt player to hit or stand, since the first two cards will never result in bust, you can always ask hit or stand here
    // if else for player response, hit or stand.
    // if hit, add one card to playerHand and getHandValue,
    //      if >21 call playerLosesHand(), if less restart phase.
    // if stand, it goes to the next phase.

    // Next phase is dealing with the dealerHand
    // this part is automatic, we don't need prompts
    // Three outcomes:
    //      dealerHandValue is <17, add card to dealerHand and restart phase
    //      dealerHandValue is >=17 && <=21, go to next phase: comparing dealer and player hands.
    //      dealerHandValue is >21, dealer bust go to playerWinsHand().

    // Next phase is for comparing dealer and player hand int values
    //      if playerHandValue==DealerHandValue, tie() ending method called
    //      if else playerHandValue>DealerHandValue, playerWinsHand() ending
    //      else, playerLosesHand() since it is the only option left.

    // At this point we shouldn't need anything, because our endOfRound, should prompt and call game.start()

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