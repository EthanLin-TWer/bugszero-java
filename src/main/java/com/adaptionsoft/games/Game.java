package com.adaptionsoft.games;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
    ArrayList<String> players = new ArrayList<>();
    ArrayList<Player> tempPlayers = new ArrayList<>();
    int[] goldCoins = new int[6];
    boolean[] inPenaltyBox = new boolean[6];

    LinkedList<String> popQuestions = new LinkedList<>();
    LinkedList<String> scienceQuestions = new LinkedList<>();
    LinkedList<String> sportsQuestions = new LinkedList<>();
    LinkedList<String> rockQuestions = new LinkedList<>();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

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

    public void addPlayer(String playerName) {
        players.add(playerName);
        tempPlayers.add(new Player(playerName));
        inPenaltyBox[howManyPlayers()] = false;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        System.out.println(getCurrentPlayer() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (inPenaltyBox[currentPlayer]) {
            if (roll % 2 != 0) {
                getOutOfPenaltyBox();
                movePlayer(roll);
                askQuestion();
            } else {
                stayInPenaltyBox();
            }
        } else {
            movePlayer(roll);
            askQuestion();
        }

    }

    private void stayInPenaltyBox() {
        isGettingOutOfPenaltyBox = false;
        System.out.println(getCurrentPlayer() + " is not getting out of the penalty box");
    }

    private void getOutOfPenaltyBox() {
        isGettingOutOfPenaltyBox = true;
        System.out.println(getCurrentPlayer() + " is getting out of the penalty box");
    }

    private void movePlayer(int roll) {
        tempPlayers.get(currentPlayer).place = getCurrentPlace() + roll;
        if (getCurrentPlace() > 11) tempPlayers.get(currentPlayer).place = getCurrentPlace() - 12;
        System.out.println(getCurrentPlayer()
                + "'s new location is "
                + getCurrentPlace());
        System.out.println("The category is " + currentCategory());
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

    private int getCurrentPlace() {
        return tempPlayers.get(currentPlayer).place;
    }

    public boolean wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayer]) {
            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                nextPlayer();
                gainGoldCoin();

                return didPlayerWin();
            } else {
                nextPlayer();
                return true;
            }
        } else {

            System.out.println("Answer was correct!!!!");
            gainGoldCoin();

            boolean winner = didPlayerWin();
            nextPlayer();

            return winner;
        }
    }

    private void gainGoldCoin() {
        goldCoins[currentPlayer]++;
        System.out.println(getCurrentPlayer()
                + " now has "
                + goldCoins[currentPlayer]
                + " Gold Coins.");
    }

    private void nextPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(getCurrentPlayer() + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        nextPlayer();
        return true;
    }

    private String getCurrentPlayer() {
        return tempPlayers.get(currentPlayer).getName();
    }


    private boolean didPlayerWin() {
        return !(goldCoins[currentPlayer] == 6);
    }
}
