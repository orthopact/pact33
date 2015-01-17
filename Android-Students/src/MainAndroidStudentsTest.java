import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class MainAndroidStudentsTest {
	MainAndroidStudents mas;
	@Before
	public void beforeTests() {
		mas = new MainAndroidStudents(1);
		
	}
	
	/**
	 * Tests if correct words can be found in the dictionnary
	 */
	@Test
	public void testOKDict() {
		assertTrue(mas.isCorrect("aller"));
		assertTrue(mas.isCorrect("zygomatique"));
		assertFalse(mas.isCorrect("qsdq"));
	}
	
	/**
	 * Tests the performance
	 */
	@Test
	public void testPerfDict() {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++)
			mas.isCorrect("zygomatique");
		long stopTime = System.currentTimeMillis();
		System.out.println(startTime + "    " + stopTime);
		assertTrue(Math.abs(startTime - stopTime) < 10);
				
	}
}
 