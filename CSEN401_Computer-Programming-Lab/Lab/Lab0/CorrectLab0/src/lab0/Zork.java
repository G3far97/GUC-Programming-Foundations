package lab0;

import java.util.Random;

public class Zork {

	public int DICE_IN_HAND = 6;

	public static int rollDice() {
		return (new Random()).nextInt(6) + 1;
	}

	public static int[] rollHand(int times) {
		int dice[] = new int[times]; // changed this to 6 in student version
		for (int i = 0; i < dice.length; i++) {
			dice[i] = rollDice();
		}
		return dice;
	}

	// count the each side to the dice (0 to 5)
	public static int[] countDice(int[] roll) {
		int[] count = new int[6];
		int i = 0;
		while (i < roll.length) {
			count[roll[i]-1]++; // removed - 1 in student version
			i++;
		}
		return count;
	}
	
	public static int getScore (int[] count) {
		int points = 0;
		// for every 3 ones you get 1000 points
		while (count[0]>=3) { // changed to if in student version
			count[0]-=3;
			points+=1000;
		}
		int i = 1;
		// for every three of anything else you get 100 * the dice
		while (i < count.length) { // made it <= in student version
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
}
