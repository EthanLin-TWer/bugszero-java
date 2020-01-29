package com.adaptionsoft.games;

public class Player {
    private final String name;
    private int goldCoins = 0;
    private int place = 0;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void increaseGoldCoin() {
        goldCoins += 1;
    }

    public int getGoldCoins() {
        return goldCoins;
    }

    public int getPlace() {
        return place;
    }

    public void moveForward(int roll) {
        place += roll;
        if (place > 11)
            place = place - 12;
    }
}
