package com.adaptionsoft.games;

import java.util.Random;

public class GameRunner {
	public static void main(String[] args) {
		Random rand = new Random();
		Game aGame = new Game();

		aGame.add("Chet");
		aGame.add("Pat");
		aGame.add("Sue");

		aGame.start(rand);
	}
}
