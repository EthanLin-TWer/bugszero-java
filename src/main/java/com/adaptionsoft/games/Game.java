package com.adaptionsoft.games;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class Game {
    PlayerContainer players = new PlayerContainer();
    HashMap<Category, LinkedList<String>> questionMap = new HashMap<>();

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

    public void run(Random rand) {
        while (players.nobodyWin()) {
            roll(rand.nextInt(5) + 1);
            if (rand.nextInt(9) == 7) {
                wrongAnswer();
            } else {
                correctAnswer();
            }
            players.nextPlayer();
        }
    }

    public void addPlayer(String playerName) {
        players.addPlayer(playerName);
    }

    public void roll(int roll) {
        System.out.println(getCurrentPlayerName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (isCurrentPlayerInPenaltyBox()) {
            if (roll % 2 != 0) {
                players.getCurrentPlayer().getOutOfPenaltyBox();
                players.getCurrentPlayer().moveTo(roll);
                askQuestion();
            } else {
                players.getCurrentPlayer().stayInPenaltyBox();
            }
        } else {
            players.getCurrentPlayer().moveTo(roll);
            askQuestion();
        }
    }

    private void askQuestion() {
        Category currentCategory = Category.getCurrentCategory(getCurrentPlace());
        System.out.println("The category is " + currentCategory.getValue());
        System.out.println(questionMap.get(currentCategory).removeFirst());
    }

    public void correctAnswer() {
        if (!isCurrentPlayerInPenaltyBox()) {
            System.out.println("Answer was correct!!!!");
            players.getCurrentPlayer().gainGoldCoin();
        }
    }

    public void wrongAnswer() {
        if (!isCurrentPlayerInPenaltyBox()) {
            System.out.println("Question was incorrectly answered");
            players.getCurrentPlayer().sendToPenaltyBox();
        }
    }

    private int getCurrentPlace() {
        return players.getCurrentPlayer().getPlace();
    }

    private String getCurrentPlayerName() {
        return players.getCurrentPlayer().getName();
    }

    private boolean isCurrentPlayerInPenaltyBox() {
        return players.getCurrentPlayer().isInPenaltyBox();
    }
}
