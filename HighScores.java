import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/* lstting
 * cis 120 f18
 * hw 09
*/

public class HighScores {
	private ArrayList<Score> highScores;
	private TextScanner t;
	
	public HighScores(TextScanner t) {
		if (t == null) {
			throw new IllegalArgumentException();
		}
		highScores = new ArrayList<Score>();
		this.t = t;
		
		read();		// read file
	}
	
	public void read() {
		String n = "";			// name
		int sc = -1;			// score
		
		while (t.hasNext()) {
			String s = t.next();			// next string from scanner
			
			if (TextScanner.isName(s)) {	// if s is a valid name
				n = s; 						// set name to s
				sc = -1;					// reset sc to -1 (to get ready for next int reading)
			} else {
				sc = Integer.parseInt(s);	// if s is an int
				highScores.add(new Score(n, sc));	// add in score with previously stored name
				n = "";						// reset n to "" (to get ready for next name reading)
			}
		}
		
		if (highScores.size() < 5) {					// if doc has fewer than 5 scores
			int diff = 5 - highScores.size();
			for (int i = 0; i < diff; i++) {			
				highScores.add(new Score("", -1));		// put in filler scores
			}
		}
	}
	
	public static HighScores make(String file) throws IOException {
		Reader r = new FileReader(file);
		HighScores h = new HighScores(new TextScanner(r));
		
		if (file == null) {
			throw new FileNotFoundException();
		}
		
		if (r != null) {
			r.close();
		}
		
		return h;
	}

	public int getSize() {
		return highScores.size();
	}
	
	public ArrayList<Score> getScores() {
		sort();
		return highScores;
	}
	
	public void sort() {
		Collections.sort(highScores, Collections.reverseOrder());	// descending order
	}
}
