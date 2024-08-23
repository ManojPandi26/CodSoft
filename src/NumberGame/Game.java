package NumberGame;

import java.util.Random;
import java.util.Scanner;

public class Game {

	private final int min = 1;
	private final int max = 100;
	private final int maxAttempts = 10;
	private final int totalRounds = 3;
	private final int roundwinpoints = 100;
	private Scanner sc = new Scanner(System.in);
	private Random random = new Random();

	public void play() {
		System.out.println("..... WELCOME TO THE GUESSING GAME .....");
		printRules();

		int score = 0;
		for (int round = 1; round <= totalRounds; round++) {
			int roundscore = playRound(round);
			if (roundscore == -1) {
				System.out.println("...*Hard Luck!*...");
				break;
			}
			score += roundscore;
		}
		System.out.println("\nGame Over!..");
		System.out.println("Your Final Score is :" + score);
	}

	private void printRules() {
		System.out.println("Rules:");
		System.out.println("- You will have " + totalRounds + " to Guess a number between " + min + " and " + max);
		System.out.println("- Each round, you will have " + maxAttempts + " attempts to guess the number.");
		System.out.println("- You will be prompted to enter your Guess.");
		System.out.println("- You will recieve feedback on whether your guess is too high ,too low, correct.");
		System.out.println("- Your score will be based on the number of rounds you win.");
		System.out.println("- Scoring:");
		System.out.println("- You will earn " + roundwinpoints + " points for winning a round.");
		System.out.println("- You can earn additional points based on the number of attempts used.");
		System.out.println("##GOOD LUCK!..");

	}

	private int playRound(int roundnum) {
		System.out.println("\nRound :" + roundnum);
		int SysGuess = random.nextInt(max - min + 1) + min;
		int attempts = 0;
		while (attempts < maxAttempts) {
			System.out.println("Enter your Guess :");
			int UserGuess = sc.nextInt();
			attempts++;
			if (SysGuess == UserGuess) {
				System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
				return roundwinpoints + (maxAttempts - attempts) * 10;
			} else if (UserGuess < SysGuess) {
				System.out.println("Too low.");
			} else {
				System.out.println("Too high.");
			}

		}
		sc.close();
		System.out.println("Sorry, your attempts are out for Round  " + roundnum + ".");
		return -1;
	}

}
