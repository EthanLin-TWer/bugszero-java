package com.adaptionsoft.games;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Game {
    ArrayList<Player> players = new ArrayList<>();
    HashMap<Category, LinkedList<String>> questionMap = new HashMap<>();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public Game() {
        for (Category category : Category.values()) {
            LinkedList<String> list = new LinkedList<>();
            for (int i = 0; i < 50; i++) {
                String question = category.getValue() + " Question " + i;
                list.addLast(question);
            }
            questionMap.put(category, list);
        }
    }

    public void addPlayer(String playerName) {
        players.add(new Player(playerName));
        System.out.println("They are player number " + players.size());
    }

    public void roll(int roll) {
        System.out.println(getCurrentPlayerName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (players.get(currentPlayer).isInPenaltyBox) {
            if (roll % 2 != 0) {
                getOutOfPenaltyBox();
                players.get(currentPlayer).moveTo(roll);
                askQuestion();
            } else {
                stayInPenaltyBox();
            }
        } else {
            players.get(currentPlayer).moveTo(roll);
            askQuestion();
        }
    }

    private void askQuestion() {
        Category currentCategory = currentCategory();
        System.out.println("The category is " + currentCategory.getValue());
        System.out.println(questionMap.get(currentCategory).removeFirst());
    }

    private Category currentCategory() {
        if (getCurrentPlace() == 0) return Category.POP;
        if (getCurrentPlace() == 4) return Category.POP;
        if (getCurrentPlace() == 8) return Category.POP;
        if (getCurrentPlace() == 1) return Category.SCIENCE;
        if (getCurrentPlace() == 5) return Category.SCIENCE;
        if (getCurrentPlace() == 9) return Category.SCIENCE;
        if (getCurrentPlace() == 2) return Category.SPORTS;
        if (getCurrentPlace() == 6) return Category.SPORTS;
        if (getCurrentPlace() == 10) return Category.SPORTS;
        return Category.ROCK;
    }

    private void nextPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
    }

    public boolean wasCorrectlyAnswered() {
        if (players.get(currentPlayer).isInPenaltyBox) {
            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                nextPlayer();
                players.get(currentPlayer).gainGoldCoin();
                return !players.get(currentPlayer).isWin();
            } else {
                nextPlayer();
                return true;
            }
        } else {
            System.out.println("Answer was correct!!!!");
            players.get(currentPlayer).gainGoldCoin();
            boolean winner = !players.get(currentPlayer).isWin();
            nextPlayer();
            return winner;
        }
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(getCurrentPlayerName() + " was sent to the penalty box");
        players.get(currentPlayer).isInPenaltyBox = true;
        nextPlayer();
        return true;
    }

    private void stayInPenaltyBox() {
        isGettingOutOfPenaltyBox = false;
        System.out.println(getCurrentPlayerName() + " is not getting out of the penalty box");
    }

    private void getOutOfPenaltyBox() {
        isGettingOutOfPenaltyBox = true;
        System.out.println(getCurrentPlayerName() + " is getting out of the penalty box");
    }

    private int getCurrentPlace() {
        return players.get(currentPlayer).place;
    }

    private String getCurrentPlayerName() {
        return players.get(currentPlayer).getName();
    }
}
