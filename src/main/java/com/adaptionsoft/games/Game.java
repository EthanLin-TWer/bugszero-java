package com.adaptionsoft.games;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class Game {
    ArrayList<Player> players = new ArrayList<>();
    HashMap<Category, LinkedList<String>> questionMap = new HashMap<>();

    int currentPlayer = 0;

    public Game() {
        for (Category category : Category.values()) {
            LinkedList<String> list = new LinkedList<>();
            for (int i = 0; i < 50; i++) {
                String question = category.getValue() + " Question " + i;
                list.addLast(question);
            }
            questionMap.put(category, list);
        }
    }

    public void run(Random rand) {
        while (nobodyWin()) {
            roll(rand.nextInt(5) + 1);
            if (rand.nextInt(9) == 7) {
                wrongAnswer();
            } else {
                correctAnswer();
            }
            nextPlayer();
        }
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
                players.get(currentPlayer).getOutOfPenaltyBox();
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
        Category currentCategory = Category.getCurrentCategory(getCurrentPlace());
        System.out.println("The category is " + currentCategory.getValue());
        System.out.println(questionMap.get(currentCategory).removeFirst());
    }

    private void nextPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
    }

    public void correctAnswer() {
        if (!players.get(currentPlayer).isInPenaltyBox) {
            System.out.println("Answer was correct!!!!");
            players.get(currentPlayer).gainGoldCoin();
        }
    }

    public void wrongAnswer() {
        if (!players.get(currentPlayer).isInPenaltyBox) {
            System.out.println("Question was incorrectly answered");
            players.get(currentPlayer).sendToPenaltyBox();
        }
    }

    private void stayInPenaltyBox() {
        System.out.println(getCurrentPlayerName() + " is not getting out of the penalty box");
    }

    private int getCurrentPlace() {
        return players.get(currentPlayer).place;
    }

    private String getCurrentPlayerName() {
        return players.get(currentPlayer).getName();
    }

    private boolean nobodyWin() {
        return players.stream().noneMatch(Player::isWin);
    }
}
