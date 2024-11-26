

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import lab0.Zork;

import org.junit.Test;


public class ZorkPrivateTest {

	@Test
	public void rollingDice() {
		int dice = Zork.rollDice();
		int high = 6;
		int low = 1;
		assertTrue("Error, dice is to high", high >= dice);
		assertTrue("Error, dice is to low", low <= dice);
	}
	
	@Test
	public void rollEightDice() {
		int[] dice = Zork.rollHand(8);
		assertEquals("roll Hand should roll eight dice", 8, dice.length);
	}
	
	@Test
	public void computeScoreWithAllSixFour() {
		int[] dice = {4,4,4,4,4,4};
		int[] count = Zork.countDice(dice);
		assertEquals("Should compute score based on dice", 800, Zork.getScore(count));
	}
	
	@Test
	public void computeScoreWithAllFiveFive() {
		int[] dice = {5,5,5,5,5,2,1,3,3,3};
		int[] count = Zork.countDice(dice);
		assertEquals("Should compute score based on dice", 1000, Zork.getScore(count));
	}
}
