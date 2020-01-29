package com.adaptionsoft.games;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;
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

    public void roll(int roll) {
        System.out.println(getCurrentPlayerName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (isCurrentPlayerInPenaltyBox()) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;
                getCurrentPlayer().gettingOutOfPenaltyBox();
                movePlayerAndAskQuestion(roll);
            } else {
                System.out.println(getCurrentPlayerName() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }
        } else {
            movePlayerAndAskQuestion(roll);
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
        if (getCurrentPlace() == 0) return "Pop";
        if (getCurrentPlace() == 4) return "Pop";
        if (getCurrentPlace() == 8) return "Pop";
        if (getCurrentPlace() == 1) return "Science";
        if (getCurrentPlace() == 5) return "Science";
        if (getCurrentPlace() == 9) return "Science";
        if (getCurrentPlace() == 2) return "Sports";
        if (getCurrentPlace() == 6) return "Sports";
        if (getCurrentPlace() == 10) return "Sports";
        return "Rock";
    }

    public boolean wasCorrectlyAnswered() {
        if (isCurrentPlayerInPenaltyBox()) {
            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                getCurrentPlayer().increaseGoldCoin();
                System.out.println(getCurrentPlayerName()
                        + " now has "
                        + getCurrentGoldCoins()
                        + " Gold Coins.");

                boolean winner = didPlayerWin();
                return winner;
            } else {
                return true;
            }
        } else {
            System.out.println("Answer was correct!!!!");
            getCurrentPlayer().increaseGoldCoin();
            System.out.println(getCurrentPlayerName()
                    + " now has "
                    + getCurrentGoldCoins()
                    + " Gold Coins.");

            boolean winner = didPlayerWin();
            return winner;
        }
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

        setNextPlayer();
        return true;
    }

    private boolean didPlayerWin() {
        return getCurrentGoldCoins() != 6;
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
