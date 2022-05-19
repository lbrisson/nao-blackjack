package com.blackjack.view;

import java.util.List;

public class TableView {

    private List<Integer> scoreBoard = null;
    private int playersEndChipValue = 0;
    private int totalEarnedChips = 0;


    public TableView(List<Integer> scoreBoard, int playerChipValue) {
        this.scoreBoard = scoreBoard;
        playersEndChipValue = playerChipValue;

        if(playersEndChipValue > 100) {
            totalEarnedChips = playerChipValue - 100;
        }

    }

    public void render() {

        if (scoreBoard.isEmpty()) {
            System.out.println("No score data was found...");
        } else {
            System.out.println("Score Results Shown Below:");
            System.out.print("Dealer: " + this.scoreBoard.get(0) + " "+ "Player: " +this.scoreBoard.get(1) + "\n");
            System.out.println();
            System.out.println("Your Total Earnings is Shown Below:");
            System.out.println("Ending Chip Value: " + playersEndChipValue);
            if(totalEarnedChips > 0) {
                System.out.println("You earn a total of " + totalEarnedChips + " extra chips!");
            }
            System.out.println();

        }
    }
}