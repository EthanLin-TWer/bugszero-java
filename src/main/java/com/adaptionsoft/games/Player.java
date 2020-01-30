package com.adaptionsoft.games;

public class Player {
    private final String name;
    private int goldCoins = 0;
    private int place = 0;
    private boolean isInPenaltyBox = false;

    public Player(String name) {
        this.name = name;

        System.out.println(name + " was added");
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

    public boolean isInPenaltyBox() {
        return isInPenaltyBox;
    }

    public void increaseGoldCoin() {
        goldCoins += 1;

        System.out.println(name + " now has " + goldCoins + " Gold Coins.");
    }

    public void moveForward(int roll) {
        place += roll;
        if (place > 11) {
            place = place - 12;
        }

        System.out.println(name + "'s new location is " + place);
    }

    public void sentToPenaltyBox() {
        isInPenaltyBox = true;

        System.out.println(name + " was sent to the penalty box");
    }

    public void gettingOutOfPenaltyBox() {
        isInPenaltyBox = false;
        System.out.println(name + " is getting out of the penalty box");
    }
}
