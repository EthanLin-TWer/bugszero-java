package com.adaptionsoft.games;

public class Game {
    private final DecksManager decksManager = new DecksManager();
    private final Players players = new Players();

    public void add(String playerName) {
        final Player player = new Player(playerName);
        players.add(player);
    }

    public boolean shouldCurrentPlayerAnswerQuestion() {
        return !players.getCurrentPlayer().isInPenaltyBox();
    }

    public void roll(int roll) {
        System.out.println(getCurrentPlayerName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (isCurrentPlayerInPenaltyBox()) {
            if (isOdd(roll)) {
                players.getCurrentPlayer().gettingOutOfPenaltyBox();
                movePlayerAndAskQuestion(roll);
            } else {
                players.getCurrentPlayer().stayInPenaltyBox();
            }
        } else {
            movePlayerAndAskQuestion(roll);
        }
    }

    private boolean isOdd(int roll) {
        return roll % 2 != 0;
    }

    private Object getCurrentPlayerName() {
        return players.getCurrentPlayer().getName();
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

    public boolean wasCorrectlyAnswered() {
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

    public void setNextPlayer() {
        players.setNextPlayer();
    }

    public boolean wrongAnswer() {
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
