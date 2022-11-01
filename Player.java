/**
 * @author Sam Larsen
 * Assignment 4
 * October 31, 2022
 * Written/online sources used: none
 * Help obtained: none
 * Add the statement: I confirm that the above list of sources is complete AND that I have 
 * not talked to anyone else about the solution to this problem.\
 * 
 * Player class, creates the Player object to be used for players in bunco.
 */
public class Player {
	
	private String name;
	private int score;
	private int wins;
	
	/**
	 * 0 argument constructor
	 * Creates a computer player
	 */
	public Player() {
		name = "Computer";
		score = 0;
		wins = 0;
		
	}
	
	/**
	 * 1 argument constructor
	 * @param str name of player
	 */
	public Player(String str) {
		name = str;
		score = 0;
		wins = 0;
	}
	
	/**
	 * getScore
	 * @return score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * getName
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * addScore
	 * @param i ammount to add to score
	 */
	public void addScore(int i) {
		score += i;
	}
	
	/**
	 * reset
	 * resets points to 0
	 */
	public void reset() {
		score = 0;
	}
	
	/**
	 * addWin
	 * adds one win to player
	 */
	public void addWin() {
		wins++;
	}
	
	/**
	 * getWins
	 * @return wins
	 */
	public int getWins() {
		return wins;
	}
}
