import static org.junit.Assert.*;

import org.junit.Test;

public class CandyTest {

	@Test
	public void testSettersGetters() {
		Candy c = new Candy();
		c.setX(4);
		c.setY(4);
		c.setScore(10);
		c.setEaten();
		
		int cx = c.getX();
		int cy = c.getY();
		int cs = c.getScore();
		
		assertEquals("x coord", cx, 4);
		assertEquals("y coord", cy, 4);
		assertEquals("score", cs, 10);
		assertTrue("eaten", c.getEaten());
	}

	@Test
	public void testCompare() {
		Candy c01 = new Candy();
		Candy c02 = new Candy();
		
		c01.setScore(2);
		c02.setScore(5);
		
		assertTrue("compare", c02.compareTo(c01) > 0);
	}
}
