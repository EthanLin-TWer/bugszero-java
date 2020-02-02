package com.adaptionsoft.games;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Game {
    ArrayList<Player> players = new ArrayList<>();
    HashMap<Category, LinkedList<String>> questionMap = new HashMap<>();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public Game() {
        LinkedList<String> popQuestions = new LinkedList<>();
        LinkedList<String> scienceQuestions = new LinkedList<>();
        LinkedList<String> sportsQuestions = new LinkedList<>();
        LinkedList<String> rockQuestions = new LinkedList<>();
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast(createRockQuestion(i));
        }
        questionMap.put(Category.POP, popQuestions);
        questionMap.put(Category.SCIENCE, scienceQuestions);
        questionMap.put(Category.SPORTS, sportsQuestions);
        questionMap.put(Category.ROCK, rockQuestions);
    }

    public String createRockQuestion(int index) {
        return "Rock Question " + index;
    }

    public void addPlayer(String playerName) {
        players.add(new Player(playerName));
        System.out.println("They are player number " + players.size());
    }

    public void roll(int roll) {
        System.out.println(getCurrentPlayerName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (players.get(currentPlayer).isInPenaltyBox) {
            if (roll % 2 != 0) {
                getOutOfPenaltyBox();
                players.get(currentPlayer).moveTo(roll);
                askQuestion();
            } else {
                stayInPenaltyBox();
            }
        } else {
            players.get(currentPlayer).moveTo(roll);
            askQuestion();
        }
    }

    private void askQuestion() {
        String currentCategory = currentCategory();
        System.out.println("The category is " + currentCategory);
        if (currentCategory.equals("Pop"))
            System.out.println(questionMap.get(Category.POP).removeFirst());
        if (currentCategory.equals("Science"))
            System.out.println(questionMap.get(Category.SCIENCE).removeFirst());
        if (currentCategory.equals("Sports"))
            System.out.println(questionMap.get(Category.SPORTS).removeFirst());
        if (currentCategory.equals("Rock"))
            System.out.println(questionMap.get(Category.ROCK).removeFirst());
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

    private void nextPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
    }

    public boolean wasCorrectlyAnswered() {
        if (players.get(currentPlayer).isInPenaltyBox) {
            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                nextPlayer();
                players.get(currentPlayer).gainGoldCoin();
                return !players.get(currentPlayer).isWin();
            } else {
                nextPlayer();
                return true;
            }
        } else {
            System.out.println("Answer was correct!!!!");
            players.get(currentPlayer).gainGoldCoin();
            boolean winner = !players.get(currentPlayer).isWin();
            nextPlayer();
            return winner;
        }
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(getCurrentPlayerName() + " was sent to the penalty box");
        players.get(currentPlayer).isInPenaltyBox = true;
        nextPlayer();
        return true;
    }

    private void stayInPenaltyBox() {
        isGettingOutOfPenaltyBox = false;
        System.out.println(getCurrentPlayerName() + " is not getting out of the penalty box");
    }

    private void getOutOfPenaltyBox() {
        isGettingOutOfPenaltyBox = true;
        System.out.println(getCurrentPlayerName() + " is getting out of the penalty box");
    }

    private int getCurrentPlace() {
        return players.get(currentPlayer).place;
    }

    private String getCurrentPlayerName() {
        return players.get(currentPlayer).getName();
    }
}
