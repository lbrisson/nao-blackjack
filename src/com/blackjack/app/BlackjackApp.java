package com.blackjack.app;
//import com.apps.util.Prompter;

import com.blackjack.model.Dealer;
import com.blackjack.model.Table;
import com.blackjack.view.TableView;

import java.util.Scanner;
//import com.apps.util.SplashApp;



/*
 *This is a the Controller.
 * It orchestrates the overall flow of the application.
 *It prompts the user for input, and then passes that input into the system ("Model").
 * ALL PROMPTS SHOULD COME FROM HERE
 */
public class BlackjackApp {
    //Prompter prompter = new Prompter(new Scanner(System.in));
    Boolean gameOver = false;
   // private Player player;
    private Dealer dealer;
    Table table;



    private final Scanner scanner = new Scanner(System.in);


   // @Override
    public void start() {
        welcome();
        showTable();

        //prompt user to enter name
       // String playerName = promptForPlayerName();

        //player = new Player(playerName);
        dealer = new Dealer();
       // table = new Table(player, dealer);

        //prompt player to see if they want to play BlackJack
       // String playGameOrNot = promptForPlayBlackJackOrNot();

//        if(playGameOrNot.equals("Y")) {
//          //  continue;
//        }

//        if(playGameOrNot.equals("N")) {
//            showNoGamesPlayedResults();
//        }

        while (!gameOver) {

            table.blackjackCheck();
           // table.

            //prompt player to Hit or Stand
            //String hitOrStand = promptForHitOrStand();
//            if(hitOrStand.equals("H")) {
//                //run hitProcessMethod()
//            }
//            if(hitOrStand.equals("S")) {
//                //run standMethod();
//            }

            //prompt player to endGame or Continue playing
            System.out.println();
         //   String endOrContinueGame = promptForEndGameOrContinue();

//            if (endOrContinueGame.equals("N")) {
//                gameOver = true;
//                showEndOfGameResults();
//            } else {
//                continue;
//            }
        }

    }

//    private String promptForPlayerName() {
=======
//class BlackjackApp implements SplashApp {
//    public static void main(String[] args) {
//        Game blackjack = new Game();
//        blackjack.welcome();
//        blackjack.start();
//    }

    private void showNoGamesPlayedResults() {
        System.out.println();
    }

//    private String promptForPlayBlackJackOrNot() {
//     //   String redealOrEndGame = prompter.prompt("Enter (Y)es to continue playing or (N)o to end the Game");
//      //  return redealOrEndGame;
//    }

//    private String promptForHitOrStand() {
//    }

    private void showEndOfGameResults() {
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

//    private String promptForEndGameOrContinue() {
//        //clear or reset appropriate variables
//        //prompt for redeal, or end game
//       // String redealOrEndGame = prompter.prompt("Enter (Y)es to continue playing or (N)o to end the Game");
//        return redealOrEndGame;
//    }

}
=======
//    public void welcome() {
//        //show welcome graphic and give prompt for asking to play
//    }
//}

