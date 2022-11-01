import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Sam Larsen
 * Assignment 4
 * October 31, 2022
 * Written/online sources used: none
 * Help obtained: none
 * Add the statement: I confirm that the above list of sources is complete AND that I have 
 * not talked to anyone else about the solution to this problem.
 * 
 * BuncoGame class, runs the main game of bunco
 */
public class BuncoGame {
	
	/**
	 * checkCont
	 * Checks to see if the current player keeps rolling
	 * @param dc The current dice cup used in the game, with the current dice rolls
	 * @param i The round, to compare if one of the dice matches the round
	 * @return Boolean to determine whether the current player continues rolling on this turn
	 */
	public static boolean checkCont(DiceCup dc, int i) {
		if(dc.getCup().get(0).getCurrentSide() == i 
				|| dc.getCup().get(1).getCurrentSide() == i 
				|| dc.getCup().get(2).getCurrentSide() == i
				|| dc.isAMiniBunco(i)) {
			return true;
		}
		return false;
	}
	
	/**
	 * playRound
	 * plays a round of bunco for the current player
	 * 
	 * @param p the index of the current player in players
	 * @param i the round number
	 */
	public static void playRound(int p, int i) {
		System.out.println(players[p].getName()+ "'s Turn");
		boolean cont = true;
		while(cont == true) {
			System.out.println("Press enter to roll");
			try {
				sc.nextLine();
			}
			catch(NoSuchElementException e) {
				System.err.println(e);
			}
			catch(IllegalStateException e) {
				System.err.println(e);
			}
			dc.roll();
			if(dc.isABunco(i)){
				System.out.println("BUNCO!");
				players[p].addScore(21);
				cont = false;
				bunco = true;
			}
			else if(dc.isAMiniBunco(i)) {
				System.out.println("Mini-Bunco!");
				players[p].addScore(5);
			}
			else {
				players[p].addScore(dc.rollTotal(i));
			}
			
			if(!checkCont(dc, i)) {
				cont = false;
			}
			System.out.println(players[p].getName()+ "'s roll: "  + dc.printRoll());
			System.out.println(players[p].getName()+ "'s Score: " + players[p].getScore());
		}
		
	}
	
	/**
	 * uniqueName
	 * Checks to see if the entered name is unique from the previously entered names
	 * @param s Entered name
	 * @param i Number of players eneted before this name
	 * @return Boolean determining whether the name is unique
	 */
	public static boolean uniqueName(String s, int i) {
		for(int j = 0; j < i; j++) {
			if(s.equals(players[j].getName())) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * getPlayers
	 * Gets the number and names of the players
	 */
	public static void getPlayers() {
		String name = "Player";
		System.out.println("How many players?(1-4)");
		try {
			playercount = sc.nextInt();
			sc.nextLine();
		}
		catch(InputMismatchException e) {
			System.err.println(e);
		}
		catch(NoSuchElementException e) {
			System.err.println(e);
		}
		catch(IllegalStateException e) {
			System.err.println(e);
		}
		if(playercount == 1) {
			playercount = 2;
			players = new Player[2];
			players[0] = new Player();
			name = "Player";
			System.out.println("Enter your name");
			try {
				name = sc.nextLine();
			}
			catch(NoSuchElementException e) {
				System.err.println(e);
			}
			catch(IllegalStateException e) {
				System.err.println(e);
			}
			players[1] = new Player(name);
			
		}
		else {
			name = "Player";
			if(playercount > 4) {
				System.out.println("4 players maximum. Starting game with 4 players.");
				playercount = 4;
			}
			players = new Player[playercount];
			for(int i = 0; i < playercount; i++) {
				System.out.println("Enter player " + (i+1) + " name.");
				try {
					name = sc.nextLine();
				}
				catch(NoSuchElementException e) {
					System.err.println(e);
				}
				catch(IllegalStateException e) {
					System.err.println(e);
				}
				if(uniqueName(name, i)){
					players[i] = new Player(name);
				}
				else {
					System.out.println("Please enter a new name.");
					i--;
				}
			}
		}
	}
	
	public static Scanner sc = new Scanner(System.in);
	public static Player[] players = new Player[2];
	public static DiceCup dc = new DiceCup();
	public static boolean bunco = false;
	public static int playercount = 1;
	
	public static void main(String[] args) {
		boolean keepPlaying = true;
		int playerTracker = 0;
		String response = null;
		getPlayers();
		while(keepPlaying) {
			System.out.println("To determine who will go first all players will roll.");
			int max = 0;
			int index = 0;
			for(Player p: players) {
				dc.roll();
				for(Dice d: dc.getCup()) {
					p.addScore(d.getCurrentSide());
				}
				System.out.println(p.getName() + " rolled a " + p.getScore());
				if(p.getScore() > max) {
					max = p.getScore();
					playerTracker = index;
				}
				index++;
			}
			
			for(Player p: players) {
				p.reset();
			}
			
			for(int i = 1; i < 7; i++) {
				System.out.println();
				System.out.println("Round " + i);
				for(int j = 0; j < playercount; j++) {
					playRound(playerTracker%players.length, i);
					playerTracker++;
					System.out.println();
					if(bunco == true) {
						break;
					}
				}
				bunco = false;
			}
			
			System.out.println();
			
			for(Player p: players) {
				System.out.println(p.getName()+"'s Score: "+ p.getScore());
			}
			System.out.println();
			
			max = 0;
			index = 0;
			int winner = 0;
			boolean tie = false;
			ArrayList<Integer> tied = new ArrayList<>();
			for(Player p: players) {
				if(p.getScore() > max) {
					max = p.getScore();
					winner = index;
					tied.clear();
					tie = false;
					tied.add(index);
				}else if(p.getScore() == max) {
					tie = true;
					tied.add(index);
				}
				index++;
			}
			if(max == 0) {
				System.out.println("No one has won.");
			}
			else if(tie) {
				System.out.println("Multiple players have tied!");
				for(int i: tied) {
					System.out.println(players[i].getName() + " has won!");
					players[i].addWin();
				}
			}
			else {
				System.out.println(players[winner].getName() + " has won!");
				players[winner].addWin();
			}
			
			System.out.println();
			
			for(Player p: players) {
				System.out.println(p.getName()+" has won "+p.getWins()+" times.");
			}
			
			System.out.println();
			System.out.println("Do you want to play again?(yes/no)");
			
			try {
				response = sc.nextLine();
			}
			catch(NoSuchElementException e) {
				System.err.println(e);
			}
			catch(IllegalStateException e) {
				System.err.println(e);
			}
			if(response.toLowerCase().equals("no")) {
				keepPlaying = false;
			}
			else {
				System.out.println();
				System.out.println("Do you want to play with the same players?(yes/no)");
				try {
					response = sc.nextLine();
				}
				catch(NoSuchElementException e) {
					System.err.println(e);
				}
				catch(IllegalStateException e) {
					System.err.println(e);
				}
				if(response.toLowerCase().equals("no")) {
					getPlayers();
				}
				else {
					for(Player p: players) {
						p.reset();
					}
				}
			}
		}
	}
}
