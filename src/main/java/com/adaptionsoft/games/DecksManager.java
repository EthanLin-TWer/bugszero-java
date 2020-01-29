package com.adaptionsoft.games;

public class DecksManager {
    final Deck popDeck = new Deck(Category.POP);
    final Deck scienceDeck = new Deck(Category.SCIENCE);
    final Deck sportsDeck = new Deck(Category.SPORTS);
    final Deck rockDeck = new Deck(Category.ROCK);

    public String getNextQuestion(int place) {
        String[] categories = new String[]{ Category.POP.getName(), Category.SCIENCE.getName(), Category.SPORTS.getName(), Category.ROCK.getName() };
        String category = categories[place % 4];

        System.out.println("The category is " + category);

        if (category.equals(categories[0])) {
            return popDeck.getNextQuestion();
        }
        if (category.equals(categories[1])) {
            return scienceDeck.getNextQuestion();
        }
        if (category.equals(categories[2])) {
            return sportsDeck.getNextQuestion();
        }
        if (category.equals(categories[3])) {
            return rockDeck.getNextQuestion();
        }
        throw new Error("");
    }
}
