package com.adaptionsoft.games;

import java.util.Random;

public class GameRunner {
	private static boolean notAWinner = true;

	public static void main(String[] args) {
		Random rand = new Random();
		playGame(rand);
	}

	public static void playGame(Random rand) {
		Game aGame = new Game();

		aGame.add("Chet");
		aGame.add("Pat");
		aGame.add("Sue");

		do {
			boolean isStillInPenaltyBox = aGame.roll(rand.nextInt(5) + 1);
			final boolean isWrongAnswer = rand.nextInt(9) == 7;

			if (aGame.shouldCurrentPlayerAnswerQuestion()) {
				if (isWrongAnswer) {
					notAWinner = aGame.wrongAnswer();
				} else {
					notAWinner = aGame.wasCorrectlyAnswered();
				}
			}
			aGame.setNextPlayer();
		} while (notAWinner);
	}
}
