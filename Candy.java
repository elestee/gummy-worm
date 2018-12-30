import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/* lstting
 * cis 120 f18
 * hw 09
*/

public class Candy implements Comparable<Candy> {
	private int score;
	private int x;
	private int y;
	private boolean eaten;
	private Image candy;
	
	public Candy() {
		int xTemp = (int) (39 * Math.random());
		x = Model.SEGMENT_SIZE * (xTemp+1);
		int yTemp = (int) (39 * Math.random());
		y = Model.SEGMENT_SIZE * (yTemp+1);
		score = (int) (2 * Math.random()) + 1;	// pt range 1-3
		eaten = false;
		loadImage();
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setScore(int s) {
		score = s;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getScore() {
		return score;
	}
	
	public boolean getEaten() {
		return eaten;
	}
	
	public void setEaten() {
		eaten = true; 
	}
	
	public void loadImage() {
    	ImageIcon c = new ImageIcon(this.getClass().getResource("/img/candy.png"));
    	candy = c.getImage();
	}
	
	public void draw(Graphics g, Model m) {
		g.drawImage(candy, x, y, Model.SEGMENT_SIZE-1, Model.SEGMENT_SIZE-1, m);
	}
	
	@Override
	public int compareTo(Candy o) {
		return ((Integer) score).compareTo((Integer) o.getScore());
	}
	
	
}
