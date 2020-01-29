package com.adaptionsoft.games;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
    final Deck popDeck = new Deck("Pop");
    final Deck scienceDeck = new Deck("Science");
    final Deck sportsDeck = new Deck("Sports");
    final Deck rockDeck = new Deck("Rock");

    int currentPlayer = 0;
    private final ArrayList<Player> players = new ArrayList<>();

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
        String question = getNextQuestion();
        System.out.println(question);
    }

    private String getNextQuestion() {
        final int place = getCurrentPlace();
        String[] categories = new String[]{ Category.POP.getName(), Category.SCIENCE.getName(), Category.SPORTS.getName(), Category.ROCK.getName() };
        String category = categories[place % 4];

        System.out.println("The category is " + category);

        if (category.equals(categories[0])) {
            return popDeck.getNextQuestion();
        }
        if (category.equals(categories[1])) {
            return scienceDeck.getNextQuestion();
        }
        if (category.equals(categories[2])) {
            return sportsDeck.getNextQuestion();
        }
        if (category.equals(categories[3])) {
            return rockDeck.getNextQuestion();
        }
        throw new Error("");
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
