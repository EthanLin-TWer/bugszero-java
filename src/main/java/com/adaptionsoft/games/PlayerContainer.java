package com.adaptionsoft.games;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: lai.yi
 * Date: 2020/2/2
 * Description:
 **/
public class PlayerContainer {
    private ArrayList<Player> players = new ArrayList<>();
    private int currentPlayer = 0;

    public void addPlayer(String playerName) {
        players.add(new Player(playerName));
        System.out.println("They are player number " + players.size());
    }

    private void nextPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
    }
}
