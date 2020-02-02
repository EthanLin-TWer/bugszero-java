package com.adaptionsoft.games;

/**
 * Created with IntelliJ IDEA.
 * User: lai.yi
 * Date: 2020/2/2
 * Description:
 **/
public enum Category {
    POP("Pop"),
    SCIENCE("Science"),
    SPORTS("Sports"),
    ROCK("Rock"),
    BLUES("Blues"),
    HISTORY("History");

    private String value;

    Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Category getCurrentCategory(int place) {
        int index = place % Category.values().length;
        return Category.values()[index];
    }
}
