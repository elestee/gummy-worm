import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/* lstting
 * cis 120 f18
 * hw 09
*/

public class SuperCandy extends Candy {
	private int score;
	private int x;
	private int y;
	private boolean eaten;
	private Image sCandy;
	
	public SuperCandy() {
		int xTemp = (int) (39 * Math.random());
		x = Model.SEGMENT_SIZE * (xTemp+1);
		int yTemp = (int) (39 * Math.random());
		y = Model.SEGMENT_SIZE * (yTemp+1);
		score = (int) (5 * Math.random()) + 5; // pt range 5 - 10
		eaten = false;
		loadImage();
		System.out.println("location: " + x + ", " + y);
	}
	
	@Override
	public void loadImage() {
    	ImageIcon sc = new ImageIcon(this.getClass().getResource("/img/scandy.png"));
    	sCandy = sc.getImage();
	}
	
	@Override
	public void draw(Graphics g, Model m) {
		g.drawImage(sCandy, super.getX(), super.getY(), Model.SEGMENT_SIZE-1, Model.SEGMENT_SIZE-1, m);
	}
}
