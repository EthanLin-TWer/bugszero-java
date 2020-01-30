package com.adaptionsoft.games;

import java.util.HashMap;

public class DecksManager {
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
