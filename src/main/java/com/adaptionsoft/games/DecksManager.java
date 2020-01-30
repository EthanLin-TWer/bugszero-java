package com.adaptionsoft.games;

import jdk.nashorn.api.tree.ForOfLoopTree;

import java.util.HashMap;

public class DecksManager {
    final Deck popDeck = new Deck(Category.POP);
    final Deck scienceDeck = new Deck(Category.SCIENCE);
    final Deck sportsDeck = new Deck(Category.SPORTS);
    final Deck rockDeck = new Deck(Category.ROCK);
    final HashMap<Category, Deck> categoryDecks = new HashMap<>();

    public DecksManager() {
        for (Category category: Category.values()) {
            categoryDecks.put(category, new Deck(category));
        }
    }

    public String getNextQuestion(int place) {
        Category category = Category.get(place);
        return categoryDecks.get(category).getNextQuestion();
    }
}
