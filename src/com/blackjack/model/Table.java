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
    private Map<String, Integer> scoreboard = new TreeMap<>();
    private List<Cards> playerCards;
    private List<Cards> dealerCards;

    //------------------CONSTRUCTORS------------------//
    public Table(Player player, Dealer dealer) {
        this.player = player;
        this.dealer = dealer;
    }

    //------------------BUSINESS METHODS------------------//
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

    public int checkPlayerHandValue() {
        int totalCardScore = 0;
        int acesInHandTotal = 0;

        for (Cards card : playerCards) {
            totalCardScore += card.getCardValue();
        }

        if (totalCardScore > 21) {
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

    public int checkDealerHandValue() {
        int totalCardScore = 0;
        int acesInHandTotal = 0;

        for (Cards card : dealerCards) {
            totalCardScore += card.getCardValue();
        }

        if (totalCardScore > 21) {
            for (Cards card : dealerCards) {
                if (card.getCardName().equals(Cards.ACE.getCardName())) {
                    acesInHandTotal++;
                }
            }
        }

        if (acesInHandTotal > 0) {
            totalCardScore = 0;
        }

        if (acesInHandTotal == 1) {
            for (Cards card : dealerCards) {
                totalCardScore += card.getCardValue();
            }
            totalCardScore -= 10;
        }
        if (acesInHandTotal == 2) {
            for (Cards card : dealerCards) {
                totalCardScore += card.getCardValue();
            }
            if (playerCards.size() > 2) {
                totalCardScore -= 20;
            } else {
                totalCardScore -= 10;
            }
        }
        if (acesInHandTotal == 3) {
            for (Cards card : dealerCards) {
                totalCardScore += card.getCardValue();
            }
            if (playerCards.size() > 3) {
                totalCardScore -= 30;
            } else {
                totalCardScore -= 20;
            }
        }
        if (acesInHandTotal == 4) {
            for (Cards card : dealerCards) {
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

    public void playerHits() {
        addToPlayerCards();
    }

    public void playerLosesRound() {
        dealerScore += 1;
        updateScoreBoard();
        System.out.println("Player's hand: " +getPlayerCards() + ", Hand Score: " +checkPlayerHandValue());
        dealer.playerLoses();
        clearActiveCards();
        clearPotentialEarnings();
    }

    public void playerWinsRound() {
        playerScore += 1;
        updateScoreBoard();
        System.out.println("Player's hand: " +getPlayerCards() + ", Hand Score: " +checkPlayerHandValue());
        increasePlayerChipValue();
        dealer.playerWins();
        clearActiveCards();
        clearPotentialEarnings();
    }

    public void playerTied() {
        player.setChipValue(player.getChipValue() + player.currentBet);
        updateScoreBoard();
        System.out.println("Player's hand: " +getPlayerCards() + ", Hand Score: " +checkPlayerHandValue());
        dealer.playerTied();
        clearActiveCards();
        clearPotentialEarnings();
    }

    public void increasePlayerChipValue() {
        player.setChipValue(player.getChipValue() + getPotentialEarnings());
    }

    private void updateScoreBoard() {
        scoreboard.clear();

        int counter = 0;
        int playerWins = 0;
        int dealerWins = 0;

        while (counter < this.dealerScore) {
            dealerWins++;
            counter++;
        }
        scoreboard.put("Dealer", dealerWins);
        counter = 0;

        while (counter < this.playerScore) {
            playerWins++;
            counter++;
        }

        scoreboard.put("Player", playerWins);

    }

    public void clearActiveCards() {
        playerCards.clear();
        dealerCards.clear();
    }

    public void clearPotentialEarnings() {
        potentialEarnings = 0;
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
        System.out.println("Player's hand: " +getPlayerCards() + ", Hand Score: " +checkPlayerHandValue());
        System.out.println();
    }

    public void addToDealerCards() {
        Cards newCard = dealer.getNextCardFromDeck();
        this.dealerCards.add(newCard);
        System.out.println("Dealer's hand: " +getDealerCards() + ", Hand Score: " +checkDealerHandValue());
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