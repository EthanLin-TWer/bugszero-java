package com.adaptionsoft.games;

import java.util.ArrayList;

public class Game {
    int currentPlayer = 0;
    private final ArrayList<Player> players = new ArrayList<>();
    private final DecksManager decksManager = new DecksManager();
    private final Players temp_players = new Players();

    public void add(String playerName) {
        final Player player = new Player(playerName);
        players.add(player);
        temp_players.add(player);
        System.out.println("They are player number " + this.players.size());
    }

    public boolean roll(int roll) {
        System.out.println(getCurrentPlayerName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (isCurrentPlayerInPenaltyBox()) {
            if (roll % 2 != 0) {
                getCurrentPlayer().gettingOutOfPenaltyBox();
                movePlayerAndAskQuestion(roll);
                return false;
            } else {
                System.out.println(getCurrentPlayerName() + " is not getting out of the penalty box");
                return true;
            }
        } else {
            movePlayerAndAskQuestion(roll);
            return false;
        }
    }

    private Object getCurrentPlayerName() {
        return getCurrentPlayer().getName();
    }

    private void movePlayerAndAskQuestion(int roll) {
        getCurrentPlayer().moveForward(roll);
        askQuestion();
    }

    private void askQuestion() {
        String question = decksManager.getNextQuestion(getCurrentPlace());
        System.out.println(question);
    }

    public boolean wasCorrectlyAnswered() {
        if (isCurrentPlayerInPenaltyBox()) {
            return true;
        }

        answeredCorrect();
        return !didPlayerWin();
    }

    private void answeredCorrect() {
        System.out.println("Answer was correct!!!!");
        getCurrentPlayer().increaseGoldCoin();
    }

    public void setNextPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()) {
            currentPlayer = 0;
        }
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        getCurrentPlayer().sentToPenaltyBox();
        return true;
    }

    private boolean didPlayerWin() {
        return getCurrentGoldCoins() == 6;
    }

    private int getCurrentPlace() {
        return getCurrentPlayer().getPlace();
    }

    private int getCurrentGoldCoins() {
        return getCurrentPlayer().getGoldCoins();
    }

    private boolean isCurrentPlayerInPenaltyBox() {
        return getCurrentPlayer().isInPenaltyBox();
    }

    private Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }
}
