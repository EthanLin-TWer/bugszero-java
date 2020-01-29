package com.adaptionsoft.games;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
    int[] places = new int[6];
    boolean[] inPenaltyBox = new boolean[6];

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
        places[howManyPlayers()] = 0;
        inPenaltyBox[howManyPlayers()] = false;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        System.out.println(getCurrentPlayerName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (isCurrentPlayerInPenaltyBox()) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                System.out.println(getCurrentPlayerName() + " is getting out of the penalty box");
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
        return players.get(currentPlayer).getName();
    }

    private void movePlayerAndAskQuestion(int roll) {
        places[currentPlayer] = getCurrentPlace() + roll;
        if (getCurrentPlace() > 11)
            places[currentPlayer] = getCurrentPlace() - 12;

        System.out.println(getCurrentPlayerName()
                + "'s new location is "
                + getCurrentPlace());
        System.out.println("The category is " + currentCategory());
        askQuestion();
    }

    private void askQuestion() {
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
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;
                players.get(currentPlayer).increaseGoldCoin();
                System.out.println(getCurrentPlayerName()
                        + " now has "
                        + getCurrentGoldCoins()
                        + " Gold Coins.");

                return didPlayerWin();
            } else {
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;
                return true;
            }
        } else {
            System.out.println("Answer was correct!!!!");
            players.get(currentPlayer).increaseGoldCoin();
            System.out.println(getCurrentPlayerName()
                    + " now has "
                    + getCurrentGoldCoins()
                    + " Gold Coins.");

            boolean winner = didPlayerWin();
            currentPlayer++;
            if (currentPlayer == players.size()) currentPlayer = 0;
            return winner;
        }
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(getCurrentPlayerName() + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
        return true;
    }

    private boolean didPlayerWin() {
        return !(getCurrentGoldCoins() == 6);
    }

    private int getCurrentPlace() {
        return places[currentPlayer];
    }

    private int getCurrentGoldCoins() {
        return players.get(currentPlayer).getGoldCoins();
    }

    private boolean isCurrentPlayerInPenaltyBox() {
        return inPenaltyBox[currentPlayer];
    }
}
