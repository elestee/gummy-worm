import java.io.*;
import java.util.Iterator;

/* lstting
 * cis 120 f18
 * hw 09
*/

public class TextScanner implements Iterator<String> {
	private BufferedReader b;
	private int x;
	private String text;
	private boolean letter;
	
	public TextScanner(Reader r) {
		if (r == null) {
			throw new IllegalArgumentException();
		}
		
		try {
			b = new BufferedReader(r);
			text = "";
			x = b.read();			// read next char
			letter = isLetter(x);	// set bool to match with current char
			if (x != -1) {			// if there is another letter to be read
				text += (char) x;	// start saving new piece of info
			}
		} catch (IOException io) {
			io.printStackTrace();
		}
	}
	
	public boolean getState() {
		return letter;
	}
	
	public static boolean isLetter(int c) {	// check if current char is alphabet char
		if (c >= 97 && c <= 122) {
			return true;
		}
		return false;
	}
	
	public static boolean isName(String s) { // check if current word is name or score
		for (int i = 0; i < s.length(); i++) {
			if (!isLetter(s.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isValid(int c) {
		if (isLetter(c) || (c >= 48 && c <= 57)) {
			return true;
		}
		return false;
	}
	
	public void close() {
		try {
			b.close();
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	@Override
	public boolean hasNext() {
		try {
			x = b.read();
			while (x != -1 && isValid(x) && letter == isLetter(x)) { // valid char that matches current state
				text += (char) x;	// add to current piece of info
				x = b.read();		// read next char
			}
			letter = !letter;		// else, flip state (from letter to int, or from int to letter)
			if (!text.equals("")) {	
				return true;		// if the saved piece of test is nonempty, then there exists info to be read
			}
			
		} catch (IOException io) {
			io.printStackTrace();
		}
		
		return false;
	}

	@Override
	public String next() {
		String temp = text;
		
		if (x != -1) {
			text = "" + (char) x;	// reset text for next piece of info
		} else {
			text = "";
		}

		return temp;
	}
	
}