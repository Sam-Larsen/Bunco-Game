import java.util.ArrayList;

/**
 * @author Sam Larsen
 * Assignment 4
 * October 31, 2022
 * Written/online sources used: none
 * Help obtained: none
 * Add the statement: I confirm that the above list of sources is complete AND that I have 
 * not talked to anyone else about the solution to this problem.
 * 
 * DiceCup class, creates the DiceCup object to be used to play Bunco
 */
public class DiceCup {
	
	private int numDice;
	private ArrayList<Dice> cup;
	
	/**
	 * 0 argument constuctor
	 * Creates a dice cup with 3 6 sided dice
	 */
	public DiceCup() {
		numDice = 3;
		cup = new ArrayList<>(numDice);
		for(int i = 0; i < numDice; i++) {
			cup.add(new Dice());
		}
	}
	
	/**
	 * 1 argument constructor
	 * @param n number of dice in the dice cup
	 */
	public DiceCup(int n) {
		numDice = n;
		cup = new ArrayList<>(numDice);
		for(int i = 0; i < numDice; i++) {
			cup.add(new Dice());
		}
	}
	
	/**
	 * 2 argument constructor
	 * @param n number of dice
	 * @param s number of sides on each dice
	 */
	public DiceCup(int n, int s) {
		numDice = n;
		cup = new ArrayList<>(numDice);
		for(int i = 0; i < numDice; i++) {
			cup.add(new Dice(s));
		}
	}
	
	/**
	 * getCup
	 * @return the cup of dice
	 */
	public ArrayList<Dice> getCup(){
		return cup;
	}
	
	/**
	 * getNumDice
	 * @return the number of dice
	 */
	public int getNumDice() {
		return numDice;
	}
	
	/**
	 * roll
	 * rolls each die in cup
	 */
	public void roll() {
		for(Dice d: cup) {
			d.roll();
		}
	}
	
	/**
	 * isABunco
	 * @param i round number
	 * @return boolean to determine if current roll is a bunco
	 */
	public boolean isABunco(int i) {
		if(cup.get(0).getCurrentSide() == i && cup.get(1).getCurrentSide() == i && cup.get(2).getCurrentSide() == i) {
			return true;
		}
		return false;
	}
	
	/**
	 * isAMiniBunco
	 * @param i round number
	 * @return boolean to determine if current roll is a mini bunco
	 */
	public boolean isAMiniBunco(int i) {
		if(cup.get(0).getCurrentSide() == cup.get(1).getCurrentSide() && cup.get(1).getCurrentSide() == cup.get(2).getCurrentSide() && !isABunco(i)) {
			return true;
		}
		return false;
	}
	
	/**
	 * rollTotal
	 * @param i round number
	 * @return int total: the total points scored on the current roll
	 */
	public int rollTotal(int i) {
		int total = 0;
		for(Dice d: cup) {
			if(d.getCurrentSide() == i) {
				total++;
			}
		}
		return total;
	}
	
	/**
	 * printRoll
	 * @return String of the roll to be readable.
	 */
	public String printRoll() {
		return cup.get(0).getCurrentSide() + ", " + cup.get(1).getCurrentSide() + ", " + cup.get(2).getCurrentSide();
	}
	
}
