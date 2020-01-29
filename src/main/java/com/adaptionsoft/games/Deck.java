package com.adaptionsoft.games;

import java.util.LinkedList;

public class Deck {
    public LinkedList<String> popQuestions = new LinkedList<String>();

    public Deck() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
        }
    }
}
