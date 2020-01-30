package com.adaptionsoft.games;

import java.util.Random;

public class Game {
    private final DecksManager decksManager = new DecksManager();
    private final Players players = new Players();

    void start(Random rand) {
        boolean notAWinner = true;
        do {
            roll(rand.nextInt(5) + 1);
            final boolean isWrongAnswer = rand.nextInt(9) == 7;

            if (shouldCurrentPlayerAnswerQuestion()) {
                notAWinner = isWrongAnswer ? wrongAnswer() : correctAnswer();
            }
            players.setNextPlayer();
        } while (notAWinner);
    }

    public void add(String playerName) {
        players.add(new Player(playerName));
    }

    private boolean shouldCurrentPlayerAnswerQuestion() {
        return !players.getCurrentPlayer().isInPenaltyBox();
    }

    private void roll(int roll) {
        System.out.println(players.getCurrentPlayer().getName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (!isCurrentPlayerInPenaltyBox()) {
            movePlayerAndAskQuestion(roll);
            return;
        }

        if (isOdd(roll)) {
            players.getCurrentPlayer().gettingOutOfPenaltyBox();
            movePlayerAndAskQuestion(roll);
            return;
        }

        players.getCurrentPlayer().stayInPenaltyBox();
    }

    private boolean isOdd(int number) {
        return number % 2 != 0;
    }

    private void movePlayerAndAskQuestion(int roll) {
        players.getCurrentPlayer().moveForward(roll);
        askQuestion();
    }

    private void askQuestion() {
        final int place = players.getCurrentPlayer().getPlace();
        String question = decksManager.getNextQuestion(place);

        System.out.println(question);
    }

    private boolean correctAnswer() {
        if (isCurrentPlayerInPenaltyBox()) {
            return true;
        }

        answeredCorrect();
        return !didPlayerWin();
    }

    private void answeredCorrect() {
        System.out.println("Answer was correct!!!!");
        players.getCurrentPlayer().increaseGoldCoin();
    }

    private boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        players.getCurrentPlayer().sentToPenaltyBox();
        return true;
    }

    private boolean didPlayerWin() {
        return getCurrentGoldCoins() == 6;
    }

    private int getCurrentGoldCoins() {
        return players.getCurrentPlayer().getGoldCoins();
    }

    private boolean isCurrentPlayerInPenaltyBox() {
        return players.getCurrentPlayer().isInPenaltyBox();
    }
}
