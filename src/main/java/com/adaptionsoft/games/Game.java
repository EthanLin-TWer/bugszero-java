package com.adaptionsoft.games;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class Game {
    PlayerContainer players = new PlayerContainer();
    QuestionContainer questions = new QuestionContainer();

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

        if (!isCurrentPlayerInPenaltyBox()) {
            players.getCurrentPlayer().moveTo(roll);
            askQuestion();
            return;
        }

        if (roll % 2 != 0) {
            players.getCurrentPlayer().getOutOfPenaltyBox();
            players.getCurrentPlayer().moveTo(roll);
            askQuestion();
        } else {
            players.getCurrentPlayer().stayInPenaltyBox();
        }
    }

    private void askQuestion() {
        Category currentCategory = Category.getCurrentCategory(getCurrentPlace());
        String question = questions.getNextQuestion(currentCategory);
        System.out.println(question);
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
