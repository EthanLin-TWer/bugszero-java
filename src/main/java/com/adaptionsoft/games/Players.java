package com.adaptionsoft.games;

import java.util.ArrayList;

public class Players {
    public int currentPlayer = 0;
    private final ArrayList<Player> players = new ArrayList<>();

    public void add(Player player) {
        players.add(player);
        System.out.println("They are player number " + this.players.size());
    }

    public void setNextPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()) {
            currentPlayer = 0;
        }
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }
}
