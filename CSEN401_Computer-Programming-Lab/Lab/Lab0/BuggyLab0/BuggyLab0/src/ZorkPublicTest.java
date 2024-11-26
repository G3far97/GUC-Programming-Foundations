
import static org.junit.Assert.*;
import lab0.Zork;

import org.junit.Test;


public class ZorkPublicTest {

	@Test
	public void rollingDice() {
		int dice = Zork.rollDice();
		int high = 6;
		int low = 1;
		assertTrue("Error, dice is to high", high >= dice);
		assertTrue("Error, dice is to low", low <= dice);
	}
	
	@Test
	public void rollSevenDice() {
		int[] dice = Zork.rollHand(7);
		assertEquals("roll Hand should roll seven dice", 7, dice.length);
	}
	@Test
	public void rollSixDice() {
		int[] dice = Zork.rollHand(6);
		assertEquals("roll Hand should roll six dice", 6, dice.length);
	}
	
	@Test
	public void countDice() {
		int[] dice = {1,2,4,4,5,5};
		int[] count = {1,1,0,2,2,0};
		
		assertArrayEquals("Should Count dice", count, Zork.countDice(dice));
	}
	
	@Test
	public void computeScoreWithThreeOnes() {
		int[] dice = {1,1,1,2,4,4,5,5};
		int[] count = Zork.countDice(dice);
		assertEquals("Should compute score based on dice", 1100, Zork.getScore(count));
	}
	
	@Test
	public void computeScoreWithAllSixOnes() {
		int[] dice = {1,1,1,1,1,1};
		int[] count = Zork.countDice(dice);
		assertEquals("Should compute score based on dice", 2000, Zork.getScore(count));
	}
	
	@Test
	public void computeScoreWithThreeFours() {
		int[] dice = {4,4,4,6,3,2};
		int[] count = Zork.countDice(dice);
		assertEquals("Should compute score based on dice", 400, Zork.getScore(count));
	}
	
	@Test
	public void computeScoreWithAllFiveFive() {
		int[] dice = {5,5,5,5,5,2};
		int[] count = Zork.countDice(dice);
		assertEquals("Should compute score based on dice", 600, Zork.getScore(count));
	}
}
