package com.adaptionsoft.games;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: lai.yi
 * Date: 2020/2/2
 * Description:
 **/
public class QuestionContainer {
    private HashMap<Category, LinkedList<String>> questionMap = new HashMap<>();

    public String getNextQuestion(Category category) {
        return questionMap.get(category).removeFirst();
    }
}
