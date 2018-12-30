/* lstting
 * cis 120 f18
 * hw 09
*/

public class Score implements Comparable<Score> {
	private String user;
	private int score;
	
	public Score(String u, int s) {
		user = u;
		score = s;
	}
	
	public String getUser() {
		return user;
	}

	public int getScore() {
		return score;
	}
	
	@Override
	public int compareTo(Score sc) {
		if (sc == null) {
			return -1;
		}
		return (((Integer) score).compareTo((Integer) sc.getScore()));
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} 
		Score sc = (Score) o;
		
		if (sc.getScore() == this.score && (sc.getUser().equals(this.user))) {
			return true;
		}
		return false;
	}
}
