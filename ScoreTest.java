import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class ScoreTest {
	
	@Test
	public void testGetters() {
		Score s = new Score("linda", 18);
		
		assertEquals("name", "linda", s.getUser());
		assertEquals("score", 18, s.getScore());
	}

	@Test
	public void testCompareTo() {
		Score s01 = new Score("boop", 34);
		Score s02 = new Score("beep", 35);
		
		ArrayList<Score> list = new ArrayList<Score>();
		list.add(s01);
		list.add(s02);
		
		Collections.sort(list, Collections.reverseOrder());
		
		assertEquals("highest score", s02, list.get(0));
		assertEquals("lowest score", s01, list.get(1));
	}

	@Test
	public void testCompareToSortList() {
		Score s01 = new Score("boop", 34);
		Score s02 = new Score("beep", 35);
		Score s03 = new Score("plop", 40);
		
		ArrayList<Score> list = new ArrayList<Score>();
		list.add(s01);
		list.add(s02);
		list.add(s03);
		
		Collections.sort(list, Collections.reverseOrder());
		assertEquals("highest score", s03, list.get(0));
		assertEquals("lowest score", s01, list.get(2));
	}
}
