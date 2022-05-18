package com.blackjack.view;

import java.util.List;

public class TableView {

    private List<Integer> scoreBoard = null;

public TableView(List<Integer> scoreBoard) {
    this.scoreBoard = scoreBoard;

}

    public void render() {
    /**
     * Will render the following:
     * - scoreBoard
     * - playerHand
     * - dealerHand
     * - PotentialEarnings
     * - Player Cash Value
     *
     * */
        if (scoreBoard.isEmpty()) {
            System.out.println("No score data was found...");
        } else {
            for (Integer score : scoreBoard) {
                System.out.println("Player Score: " + score);
            }
        }
    }
}