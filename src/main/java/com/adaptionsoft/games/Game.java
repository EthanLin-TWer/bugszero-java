package com.adaptionsoft.games;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();

    int currentPlayer = 0;
    private final ArrayList<Player> players = new ArrayList<>();

    public Game() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast(createRockQuestion(i));
        }
    }

    public String createRockQuestion(int index) {
        return "Rock Question " + index;
    }

    public boolean add(String playerName) {
        players.add(new Player(playerName));

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;
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
        System.out.println("The category is " + currentCategory());

        if (currentCategory().equals("Pop"))
            System.out.println(popQuestions.removeFirst());
        if (currentCategory().equals("Science"))
            System.out.println(scienceQuestions.removeFirst());
        if (currentCategory().equals("Sports"))
            System.out.println(sportsQuestions.removeFirst());
        if (currentCategory().equals("Rock"))
            System.out.println(rockQuestions.removeFirst());
    }

    private String currentCategory() {
        String[] categories = new String[]{ "Pop", "Science", "Sports", "Rock" };
        if (getCurrentPlace() == 0) return categories[0];
        if (getCurrentPlace() == 4) return categories[0];
        if (getCurrentPlace() == 8) return categories[0];
        if (getCurrentPlace() == 1) return categories[1];
        if (getCurrentPlace() == 5) return categories[1];
        if (getCurrentPlace() == 9) return categories[1];
        if (getCurrentPlace() == 2) return categories[2];
        if (getCurrentPlace() == 6) return categories[2];
        if (getCurrentPlace() == 10) return categories[2];
        return categories[3];
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
