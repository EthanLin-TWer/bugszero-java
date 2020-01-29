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
        final Category typedCategory = categories[place % categories.length];
        String category = typedCategory.getName();

        System.out.println("The category is " + category);

        if (typedCategory.equals(categories[0])) {
            return categoryDecks.get(Category.POP).getNextQuestion();
        }
        if (typedCategory.equals(categories[1])) {
            return categoryDecks.get(Category.SCIENCE).getNextQuestion();
        }
        if (typedCategory.equals(categories[2])) {
            return categoryDecks.get(Category.SPORTS).getNextQuestion();
        }
        if (typedCategory.equals(categories[3])) {
            return categoryDecks.get(Category.ROCK).getNextQuestion();
        }
        throw new Error("");
    }
}
