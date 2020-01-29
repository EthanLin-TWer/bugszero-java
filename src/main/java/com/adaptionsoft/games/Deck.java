package com.adaptionsoft.games;

import java.util.LinkedList;

public class Deck {
    public LinkedList<String> questions = new LinkedList<String>();

    public Deck(String category) {
        for (int i = 0; i < 50; i++) {
            questions.addLast(category + " Question " + i);
        }
    }

    public String getNextQuestion() {
        return questions.removeFirst();
    }
}
