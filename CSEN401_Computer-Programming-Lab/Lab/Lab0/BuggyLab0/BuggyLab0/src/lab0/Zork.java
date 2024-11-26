package lab0;

import java.util.Random;

public class Zork {

	public int DICE_IN_HAND = 6;

	public static int rollDice() {
		return (new Random()).nextInt(6) + 1;
	}

	public static int[] rollHand(int times) {
		int dice[] = new int[6];
		for (int i = 0; i < dice.length; i++) {
			dice[i] = rollDice();
		}
		return dice;
	}

	/**
	 * count the occurrence of each side of the dice (0 to 5)
	 * where 0 is the side that has 1 on it and 5 is the side that has 6
	 * @param roll an array or dice rolls
	 * @return and array of count for each side of dice
	 */
	public static int[] countDice(int[] roll) {
		int[] count = new int[6];
		int i = 0;
		while (i < roll.length) {
			count[roll[i]]++;
			i++;
		}
		return count;
	}
	
	public static int getScore (int[] count) {
		int points = 0;
		// for every 3 ones you get 1000 points
		if (count[0]>=3) {
			count[0]-=3;
			points+=1000;
		}
		int i = 1;
		// for every three of anything else you get 100 * the dice
		while (i <= count.length) {
		  while(count[i]>=3) {
			  points+= (i+1)*100;  
			  count[i]-=3;
		  }
		  i++;
		}
		// for every 5 after removing all 3s you should get 50 points
		points = points + count[4]*50;
		// for every 1 after removing all 3s you should get 100 points
		points = points + count[0]*100;
		
		return points;
	}
	
	public static void main(String[] args) {
		
		int x = 3;
		System.out.println("x is"+x);
		int roll = Zork.rollDice();
		System.out.println("Roll dice and you get "+ roll);
        
//      Hint: Needed for debuggibg the first bug
        int[] dice = Zork.rollHand(7);
		
//		int[] dice = Zork.rollHand(8);
//		int[] count = Zork.countDice(dice);
//		int score = Zork.getScore(count);
//		System.out.println(score);
	}
}
