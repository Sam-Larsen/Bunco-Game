
/**
 * @author Sam Larsen
 * Assignment 4
 * October 31, 2022
 * Written/online sources used: none
 * Help obtained: none
 * Add the statement: I confirm that the above list of sources is complete AND that I have 
 * not talked to anyone else about the solution to this problem.
 * 
 * Dice class, creates dice to be rolled in BuncoGame
 */
public class Dice {
	
	private int sides;
	private int currentSide;
	
	/**
	 * 0 argument constructor
	 */
	public Dice() {
		sides = 6;
		currentSide = (int)(Math.random() * sides) + 1;
	}
	
	/**
	 * 1 argument constructor
	 * @param n number of sides on the die
	 */
	public Dice(int n) {
		sides = n;
		currentSide = (int)(Math.random() * sides) + 1;
	}
	
	/**
	 * roll
	 * rolls the die (random integer between 1 and sides)
	 * @return random integer between 1 and sides
	 */
	public int roll() {
		currentSide = (int)(Math.random() * sides) + 1;
		return currentSide;
	}
	
	/**
	 * getCurrentSide
	 * @return the current roll of the die
	 */
	public int getCurrentSide() {
		return currentSide;
	}
	
	/**
	 * getSides
	 * @return number of sides
	 */
	public int getSides() {
		return sides;
	}
}
