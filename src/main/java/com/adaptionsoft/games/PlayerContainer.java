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

    public void addPlayer(String playerName) {
        players.add(new Player(playerName));
        System.out.println("They are player number " + players.size());
    }
}
