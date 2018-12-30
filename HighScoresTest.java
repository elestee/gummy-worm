import static org.junit.Assert.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class HighScoresTest {

	@Test
	public void testRead() throws IOException {
		HighScores h;
		ArrayList<Score> hs_actual = new ArrayList<Score> ();
		
		try {
			h = HighScores.make("files/read.txt");
			hs_actual = h.getScores();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<Score> hs_expected = new ArrayList<Score>();
		hs_expected.add(new Score("linda", 5));
		hs_expected.add(new Score("monica", 4));
		hs_expected.add(new Score("james", 3));
		hs_expected.add(new Score("diana", 2));
		hs_expected.add(new Score("gillian", 1));
		Collections.sort(hs_expected, Collections.reverseOrder());
		
		for (int i = 0; i < 5; i++) {
			Score c = hs_actual.get(i);
			String p = c.getUser() + ((Integer) c.getScore()).toString();
			System.out.println(p);
		}
		
		for (int i = 0; i < 5; i++) {
			Score c = hs_expected.get(i);
			String p = c.getUser() + ((Integer) c.getScore()).toString();
			System.out.println(p);
		}
		
		assertEquals("scores", hs_expected, hs_actual);
	}

}
