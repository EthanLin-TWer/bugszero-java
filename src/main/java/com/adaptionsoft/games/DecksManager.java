package com.adaptionsoft.games;

public class DecksManager {
    final Deck popDeck = new Deck(Category.POP);
    final Deck scienceDeck = new Deck(Category.SCIENCE);
    final Deck sportsDeck = new Deck(Category.SPORTS);
    final Deck rockDeck = new Deck(Category.ROCK);

    public String getNextQuestion(int place) {
        Category[] temp_categories = Category.values();
        String category = temp_categories[place % temp_categories.length].getName();

        System.out.println("The category is " + category);

        if (category.equals(temp_categories[0].getName())) {
            return popDeck.getNextQuestion();
        }
        if (category.equals(temp_categories[1].getName())) {
            return scienceDeck.getNextQuestion();
        }
        if (category.equals(temp_categories[2].getName())) {
            return sportsDeck.getNextQuestion();
        }
        if (category.equals(temp_categories[3].getName())) {
            return rockDeck.getNextQuestion();
        }
        throw new Error("");
    }
}
