package com.blackjack.model;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Table {
    private static final int BLACKJACK = 21;
    private static final int MINIMUM_BET = 5;
    //private Player player;
    private Dealer dealer;
    private int playerScore;
    private int dealerScore;
    private int potentialEarnings;
    private Map<String, Integer> scoreboard = new TreeMap<>();//empty
    private Set<Cards> playerCards;
    private Set<Cards> dealerCards;
    private int roundCounter;//keep track of all the turns passed since game started

    /**
     * Table has its own direct player & dealer to grab & update values from
     * Maybe a playFactory/Dealer Factory can be used in Main to create them & pass them in
     */

    public Table() {

    }
//    //public Table(Player player, Dealer dealer) {
//        this.player = player;
//        this.dealer = dealer;
//    }

    //------------------BUSINESS METHODS------------------//

    private int checkPlayerHandValue() {//WILL CALCULATE HOW TO DO ONCE CARDS CLASS IS COMPLETED
        int playerCardsValue = 0;

        return playerCardsValue;
    }

    private int checkDealerHandValue() {//WILL CALCULATE HOW TO DO ONCE CARDS CLASS IS COMPLETED
        int dealerCardsValue = 0;

        return dealerCardsValue;
    }

    /**
     * Verify what current value of all active cards are in both hands
     * Passes both hand values to compareMethod() to Test for Scenarios(wins, ties, loses)
     */
    public void checkBothHandsTotalValue() {
        int playerTotalCardValue = checkPlayerHandValue(); //Return int of what the value of player hand is
        int dealerTotalCardValue = checkDealerHandValue();//Return int of what the value of dealer hand is

        compareActiveHands(playerTotalCardValue, dealerTotalCardValue);
    }

    public void blackjackCheck(){
        // check for dealer blackjack
        if (checkDealerHandValue() == BLACKJACK) {
            if (checkPlayerHandValue() == BLACKJACK) {
          //      tie();
            }
            else {
            //    dealerWin();
            }
        }
        else if (checkPlayerHandValue() == BLACKJACK) {
           // player.setBlackjack(true);
          //  playerWin();
       }
    }

    /**
     * If Player loses this method will do the following:
     *      - increase dealerScore by 1 -> within this method
     *      - update scoreboard ->updateScoreboard()**
     *      - dealer calls playerBust() method OR it gets called somewhere else? to print: "You lost this round!"
     *      - empty player & dealer hands -> clearActiveCards()**
     *      - end round - Who should call endRound??? -> endRound() Method from somewhere??
     *      - prompter method is called to end or continue game - promptPlayerMethod() from somewhere
     */
    private void playerLosesRound() {
        dealerScore += 1;
        //updateScoreBoard(); //function not completed yet
        //dealer.playerLoses()                                                                                    ?? or something else to get the print:"You Lost!"
        clearActiveCards();
        clearPotentialEarnings();
        //endRound() Method from somewhere
        //Call to prompter Method()
    }

    //playerPlacesBet Method

    /**
     * If Player wins this method will do the following:
     *      - increase playerScore by 1 -> within this method
     *      - update scoreboard -> call updateScoreboard()
     *      - dealer calls playerWin() method(OR it gets called somewhere else?) to print: "You lost this round!"
     *      - player gets paid earnings of chips -> call increasePlayerChipValue()
     *      - empty player & dealer hands -> call clearActiveCards()
     *      - reset potentialEarnings -> call clearPotentialEarnings()
     *      - end round - Who should call endRound??? -> endRound() Method from somewhere??
     *      - prompter method is called to end or continue game - promptPlayerMethod() from somewhere
     */
    private void playerWinsRound() {
        playerScore += 1;
        //updateScoreBoard(); //function not completed yet
        increasePlayerChipValue();
        //dealer.playerWin()                                                                                      ?? or something else to get the print:"You Win!"
        clearActiveCards();
        clearPotentialEarnings();
        //endRound() Method from somewhere
        //Call to prompter Method()
    }

    //Empty currentActiveCards List
    public void clearActiveCards() {
      //  playerCards.clear();
      //  dealerCards.clear();
    };

    public void clearPotentialEarnings() {
        potentialEarnings = 0;
    };

    //If player hits blackjack pay them
    public void increasePlayerChipValue() {
        //player.chipValue += getPotentialEarnings();
    };

    //Decrease player cash value each time they place bet
    public void decreasePlayerChipValue() {

    };


    /**
     * Verify what current value of all active cards are in both hands
     * Passes both hand values to compareMethod() to Test for Scenarios(wins, ties, loses)
     */
    public void compareActiveHands(int playerTotalCardValue,int dealerTotalCardValue) {

        //--------------TIE SCENARIOS----------------//
        //PLAYER && DEALER TIE SCENARIO
        if(playerTotalCardValue == BLACKJACK && dealerTotalCardValue == BLACKJACK){
            //Dealer calls TIE
            //dealer.tie();                                                                                            ???? ->OR do you want to do this some other way??
        }

        //PLAYER && DEALER BUST
        if(playerTotalCardValue > BLACKJACK && dealerTotalCardValue > BLACKJACK) {
            //TIE
            //Call dealer.tie();                                                                                       ???? ->OR do you want to do this some other way??
        }


        //----------------LOSE SCENARIOS------------------//
        //PLAYER BUST SCENARIO
        if(playerTotalCardValue > BLACKJACK) {
            //call playerLosesRound() method above
            playerLosesRound();
        }

        //DEALER 21 && PLAYER UNDER 21
        if(dealerTotalCardValue == BLACKJACK && playerTotalCardValue < BLACKJACK) {
            //call playerLosesRound() method above
            playerLosesRound();
        }

        //----------------WIN SCENARIOS------------------//
        //PLAYER 21 && DEALER UNDER 21
        if(playerTotalCardValue == BLACKJACK && dealerTotalCardValue < BLACKJACK) {
            //call playerWinsRound() method above
            playerWinsRound();
        }

        //DEALER BUST, BUT PLAYER IS UNDER
        if(dealerTotalCardValue > BLACKJACK && playerTotalCardValue < BLACKJACK) {
            //call playerWinsRound() method above
            playerWinsRound();
        }

        //----------------ROUND ONGOING SCENARIOS------------------//

        //PLAYER HAND VALUE UNDER 21 && DEALER HAND VALUE UNDER 21
        if(playerTotalCardValue < BLACKJACK && dealerTotalCardValue < BLACKJACK) {
            //Call dealer.giveDealerCard()
            //call method that asks player if: They want to hit or pass -> Call Hit or Pass Method                   -> Who owns this method as of right now?*************
            //CALL checkBothHandsTotalValue METHOD()
        }
    }


    //----------------SETTER METHODS----------------//
    public void setPotentialEarnings() {
        //this.potentialEarnings = player.currentBet * 4;
    }

    public void addToPlayerCards() {
        //  Cards newCard = dealer.givePlayerCard();
        //  this.playerCards.add();
    }

    public void addToDealerCards() {
        // Cards newCard = dealer.givePlayerCard();
        // this.dealerCards.add();
    }

    //STILL NEED TO FINISH CODE PROCESS FOR THIS
    public void updateScoreboard() {
        //NEED TO WRITE COE FOR THIS STILL
    }

    //----------------GETTER METHODS----------------//
    public int getPotentialEarnings() {
        return potentialEarnings;
    }

    public Set<Cards> getPlayerCards() {
        return (Set<Cards>) List.copyOf(playerCards);
    }

    public Set<Cards> getDealerCards() {
        return (Set<Cards>) List.copyOf(dealerCards);
    }

    public List<Integer> getScoreboard() {
        return List.copyOf(scoreboard.values());
    }
}