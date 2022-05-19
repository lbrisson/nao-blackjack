package com.blackjack.model;

import java.util.*;

public class Table {
    private static final int BLACKJACK = 21;
    private static final int MINIMUM_BET = 5;
    private Player player;
    private Dealer dealer;
    private int playerScore;
    private int dealerScore;
    private int potentialEarnings;
    private Map<String, Integer> scoreboard = new TreeMap<>();//empty
    private List<Cards> playerCards;
    private List<Cards> dealerCards;
    private int roundCounter;//keep track of all the turns passed since game started

    /**
     * Table has its own direct player & dealer to grab & update values from
     * Maybe a playFactory/Dealer Factory can be used in Main to create them & pass them in
     */


    //CONSTRUCTORS
    public Table() {
    }

    public Table(Player player, Dealer dealer) {
        this.player = player;
        this.dealer = dealer;
    }

    //------------------BUSINESS METHODS------------------//

    public int checkPlayerHandValue() {//WILL CALCULATE HOW TO DO ONCE CARDS CLASS IS COMPLETED
        int totalCardScore = 0;
        int acesInHandTotal = 0;

        for (Cards card : playerCards) {
            totalCardScore += card.getCardValue();
        }

        if (totalCardScore > 21) {
            //CHECK IF/How many ACES ARE IN HAND
            for (Cards card : playerCards) {
                if (card.getCardName().equals(Cards.ACE.getCardName())) {
                    acesInHandTotal++;
                }
            }
        }

        if (acesInHandTotal > 0) {
            totalCardScore = 0;
        }

        if (acesInHandTotal == 1) {
            for (Cards card : playerCards) {
                totalCardScore += card.getCardValue();
            }
            totalCardScore -= 10;
        }
        if (acesInHandTotal == 2) {
            for (Cards card : playerCards) {
                totalCardScore += card.getCardValue();
            }
            if (playerCards.size() > 2) {
                totalCardScore -= 20;
            } else {
                totalCardScore -= 10;
            }
        }
        if (acesInHandTotal == 3) {
            for (Cards card : playerCards) {
                totalCardScore += card.getCardValue();
            }
            if (playerCards.size() > 3) {
                totalCardScore -= 30;
            } else {
                totalCardScore -= 20;
            }
        }

        if (acesInHandTotal == 4) {
            for (Cards card : playerCards) {
                totalCardScore += card.getCardValue();
            }
            if (playerCards.size() > 4) {
                totalCardScore -= 40;
            } else {
                totalCardScore -= 30;
            }
        }

        return totalCardScore;
    }

    public int checkDealerHandValue() {//WILL CALCULATE HOW TO DO ONCE CARDS CLASS IS COMPLETED
        int totalCardScore = 0;

        for (Cards card : dealerCards) {
            totalCardScore += card.getCardValue();
        }

        return totalCardScore;
    }

    public void playerHits() {
        addToPlayerCards();
    }

    public void playerStands() {
        addToDealerCards();
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

    private void updateScoreBoard() {
        scoreboard.clear();

        int counter = 1;
        int playerWins = 0;
        int dealerWins = 0;

        while (counter < this.playerScore) {
            playerWins++;
        }

        scoreboard.put("Player Wins", playerWins);

        while (counter < this.dealerScore) {
            dealerWins++;
        }
        scoreboard.put("Dealer Wins", playerWins);
    }

    /**
     * If Player loses this method will do the following:
     * - increase dealerScore by 1 -> within this method
     * - update scoreboard ->updateScoreboard()**
     * - dealer calls playerBust() method OR it gets called somewhere else? to print: "You lost this round!"
     * - empty player & dealer hands -> clearActiveCards()**
     * - end round - Who should call endRound??? -> endRound() Method from somewhere??
     * - prompter method is called to end or continue game - promptPlayerMethod() from somewhere
     */
    public void playerLosesRound() {
        dealerScore += 1;
        updateScoreBoard();
        dealer.playerLoses();
        clearActiveCards();
        clearPotentialEarnings();
    }

    //playerPlacesBet Method

    /**
     * If Player wins this method will do the following:
     * - increase playerScore by 1 -> within this method
     * - update scoreboard -> call updateScoreboard()
     * - dealer calls playerWin() method(OR it gets called somewhere else?) to print: "You lost this round!"
     * - player gets paid earnings of chips -> call increasePlayerChipValue()
     * - empty player & dealer hands -> call clearActiveCards()
     * - reset potentialEarnings -> call clearPotentialEarnings()
     * - end round - Who should call endRound??? -> endRound() Method from somewhere??
     * - prompter method is called to end or continue game - promptPlayerMethod() from somewhere
     */
    public void playerWinsRound() {
        playerScore += 1;
        updateScoreBoard();
        increasePlayerChipValue();
        dealer.playerWins();
        clearActiveCards();
        clearPotentialEarnings();
    }

    public void playerTied() {
        player.setChipValue(player.getChipValue() + player.currentBet);
        updateScoreBoard();
        dealer.playerTied();
        clearActiveCards();
        clearPotentialEarnings();
    }

    //Empty currentActiveCards List
    public void clearActiveCards() {
        playerCards.clear();
        dealerCards.clear();
    }


    public void clearPotentialEarnings() {
        potentialEarnings = 0;
    }


    //If player hits blackjack pay them
    public void increasePlayerChipValue() {
        player.setChipValue(player.getChipValue() + getPotentialEarnings());
    }

    public void dealInitialHands() {
        playerCards = new ArrayList<>();
        dealerCards = new ArrayList<>();
        Cards newCard1 = dealer.getNextCardFromDeck();
        Cards newCard2 = dealer.getNextCardFromDeck();
        Cards newCard3 = dealer.getNextCardFromDeck();
        Cards newCard4 = dealer.getNextCardFromDeck();

        this.playerCards.add(newCard1);
        this.playerCards.add(newCard2);

        this.dealerCards.add(newCard3);
        this.dealerCards.add(newCard4);
    }


    /**
     * Verify what current value of all active cards are in both hands
     * Passes both hand values to compareMethod() to Test for Scenarios(wins, ties, loses)
     */
    public void compareActiveHands(int playerTotalCardValue, int dealerTotalCardValue) {

        //--------------TIE SCENARIOS----------------//
        //PLAYER && DEALER TIE SCENARIO
        if (playerTotalCardValue == BLACKJACK && dealerTotalCardValue == BLACKJACK) {
            //Dealer calls TIE
            playerTied();
        }

        //PLAYER && DEALER BUST
        if (playerTotalCardValue > BLACKJACK && dealerTotalCardValue > BLACKJACK) {
            //TIE
            playerTied();
        }


        //----------------LOSE SCENARIOS------------------//
        //PLAYER BUST SCENARIO
        if (playerTotalCardValue > BLACKJACK) {
            //call playerLosesRound() method above
            playerLosesRound();
        }

        //DEALER 21 && PLAYER UNDER 21
        if (dealerTotalCardValue == BLACKJACK && playerTotalCardValue < BLACKJACK) {
            //call playerLosesRound() method above
            playerLosesRound();
        }

        //----------------WIN SCENARIOS------------------//
        //PLAYER 21 && DEALER UNDER 21
        if (playerTotalCardValue == BLACKJACK && dealerTotalCardValue < BLACKJACK) {
            //call playerWinsRound() method above
            playerWinsRound();
        }

        //DEALER BUST, BUT PLAYER IS UNDER
        if (dealerTotalCardValue > BLACKJACK && playerTotalCardValue < BLACKJACK) {
            //call playerWinsRound() method above
            playerWinsRound();
        }

        //----------------ROUND ONGOING SCENARIOS------------------//

        //PLAYER HAND VALUE UNDER 21 && DEALER HAND VALUE UNDER 21
        if (playerTotalCardValue < BLACKJACK && dealerTotalCardValue < BLACKJACK) {
            //Call dealer.giveDealerCard()
            //call method that asks player if: They want to hit or pass -> Call Hit or Pass Method                   -> Who owns this method as of right now?*************
            //CALL checkBothHandsTotalValue METHOD()
        }
    }


    //----------------SETTER METHODS----------------//
    public void setPotentialEarnings() {
        if (player.blackjack) {
            this.potentialEarnings = player.currentBet * 3;
        } else {
            this.potentialEarnings = player.currentBet * 2;
        }
    }

    public void addToPlayerCards() {
        Cards newCard = dealer.getNextCardFromDeck();
        this.playerCards.add(newCard);
        System.out.println(getPlayerCards());
    }

    public void addToDealerCards() {
        Cards newCard = dealer.getNextCardFromDeck();
        this.dealerCards.add(newCard);
        System.out.println(getDealerCards());
    }

    public void increaseRoundCounter() {
        this.roundCounter++;
    }

    //STILL NEED TO FINISH CODE PROCESS FOR THIS
    public void updateScoreboard() {
        //NEED TO WRITE COE FOR THIS STILL
    }

    //----------------GETTER METHODS----------------//
    public int getPotentialEarnings() {
        return potentialEarnings;
    }

    public List<Cards> getPlayerCards() {
        return (List<Cards>) List.copyOf(playerCards);
    }

    public List<Cards> getDealerCards() {
        return (List<Cards>) List.copyOf(dealerCards);
    }

    public List<Integer> getScoreboard() {
        return List.copyOf(scoreboard.values());
    }
}