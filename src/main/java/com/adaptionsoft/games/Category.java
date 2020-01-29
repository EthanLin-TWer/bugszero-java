package com.adaptionsoft.games;

public enum Category {
    POP("Pop"),
    SCIENCE("Science"),
    SPORTS("Sports"),
    ROCK("Rock");

    private final String name;
    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
