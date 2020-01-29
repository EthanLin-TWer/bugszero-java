package com.adaptionsoft.games;

public class Player {
    private final String name;
    private int goldCoins = 0;
    private int place = 0;
    private boolean isInPenaltyBox = false;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getGoldCoins() {
        return goldCoins;
    }

    public int getPlace() {
        return place;
    }

    public void increaseGoldCoin() {
        goldCoins += 1;
    }

    public void moveForward(int roll) {
        place += roll;
        if (place > 11) {
            place = place - 12;
        }

        System.out.println(name + "'s new location is " + place);
    }
}
