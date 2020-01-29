package com.adaptionsoft.games;

import java.util.HashMap;

public class DecksManager {
    final Deck popDeck = new Deck(Category.POP);
    final Deck scienceDeck = new Deck(Category.SCIENCE);
    final Deck sportsDeck = new Deck(Category.SPORTS);
    final Deck rockDeck = new Deck(Category.ROCK);
    final HashMap<Category, Deck> categoryDecks = new HashMap<>();

    public DecksManager() {
        categoryDecks.put(Category.POP, popDeck);
        categoryDecks.put(Category.SCIENCE, scienceDeck);
        categoryDecks.put(Category.SPORTS, sportsDeck);
        categoryDecks.put(Category.ROCK, rockDeck);
    }

    public String getNextQuestion(int place) {
        Category[] categories = Category.values();
        final Category category = categories[place % categories.length];

        System.out.println("The category is " + category.getName());

        return categoryDecks.get(category).getNextQuestion();
    }
}
