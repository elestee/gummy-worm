import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/* lstting
 * cis 120 f18
 * hw 09
*/

public class Snake implements Comparable<Snake> {
    private int length;
    private int[][] position;
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    private Image blob;
    
    public Snake(int max) {
    	length = 3;
    	
        position = new int[max][2];	// 2 values (x,y) stored for each segment
        for (int i = 0; i < length; i++) {
        	position[i][0] = 50 - Model.SEGMENT_SIZE * i;	// set head x position at 50
        	position[i][1] = 200;							// set head y position at 200
        }
        
        left = false;
        right = true;	// snake starts off moving to the right
        up = false;
        down = false;
        
        loadImage();	// load snake body img
    }
    
    public int getSize() {
        return length;
    }
    
    public void incrSize() {
    	int xTail = position[length-1][0];
    	int yTail = position[length-1][1];
    	
    	if (left) {
    		position[length][0] = xTail + Model.SEGMENT_SIZE;	// set new segment x to right of tail
    	} else if (right) {
    		position[length][0] = xTail - Model.SEGMENT_SIZE;	// set new segment x to left of tail
    	}
    	if (up) {
    		position[length][1] = yTail - Model.SEGMENT_SIZE;	// set new segment y to below tail
    	} else if (down) {
    		position[length][1] = yTail + Model.SEGMENT_SIZE;	// set new segment y to above tail
    	}
    	
    	length++;	// increase length
    }
    
    public void move() {
    	for (int i = length-1; i > 0 ; i--) {
    		position[i][0] = position[i-1][0];	// move each segment up to prev segment's coords
    		position[i][1] = position[i-1][1];
    	}
    	
    	// move head accordingly
    	if (left) {
    		position[0][0] -= Model.SEGMENT_SIZE;	
    	} else if (right) {
    		position[0][0] += Model.SEGMENT_SIZE;
    	}
    	
    	if (up) {
    		position[0][1] -= Model.SEGMENT_SIZE;
    	} else if (down) {
    		position[0][1] += Model.SEGMENT_SIZE;
    	}
    }
    
    // checks if the snake has bumped into itself or into the board boundaries
    public boolean bumper() {
    	for (int i = length-1; i > 0; i--) {
    		if (position[0][0] == position[i][0] && position[0][1] == position[i][1]) {
    			return false;
    		}
    	}
    	
    	if (position[0][0] < 0 || position[0][0] > Model.BOARD_WIDTH-10 ||
    		position[0][1] < 0 || position[0][1] > Model.BOARD_HEIGHT-10) {
    		return false;
    	}
    	
    	return true;
    }
   
    public void eatCandy(Candy c) {
    	if (position[0][0] == c.getX() && position[0][1] == c.getY()) { // if overlap
        	c.setEaten();	// set boolean of candy
        	for (int i = 0; i < c.getScore(); i++) {	// increase length of snake
        		incrSize();	// for each point of the candy's score
        	}
    	}
    }
    
    public void loadImage() {
    	ImageIcon b = new ImageIcon(this.getClass().getResource("/img/body.png"));
    	blob = b.getImage();
    }
    
    public void draw(Graphics g, Model m) {
    	for (int i = 0; i < length; i++) {
    		g.drawImage(blob, position[i][0], position[i][1], Model.SEGMENT_SIZE-1, Model.SEGMENT_SIZE-1, m);
    	}
    }
    
    public boolean getLeft() {
    	return left;
    }
    
    public boolean getRight() {
    	return right;
    }
    
    public boolean getUp() {
    	return up;
    }
    
    public boolean getDown() {
    	return down;
    }
    
    public void goLeft() {
    	left = true;
    	right = false;
    	up = false;
    	down = false;
    }
    
    public void goRight() {
    	left = false;
    	right = true;
    	up = false;
    	down = false;
    }
    
    public void goUp() {
    	left = false;
    	right = false;
    	up = true;
    	down = false;
    }
    
    public void goDown() {
    	left = false;
    	right = false;
    	up = false;
    	down = true;
    }
    
	@Override
	public int compareTo(Snake o) {
		return 0;
	}
    
    
}