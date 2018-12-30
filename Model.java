/* lstting
 * cis 120 f18
 * hw 09
*/

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;

@SuppressWarnings("serial")
public class Model extends JPanel {
    private static Snake snake;	// new snake object
    private Candy candy;	// to be eaten by snake
    private int score;		// length of snake
    private boolean alive;	// life of snake
    private State state;	// to determine what componenets to be shown on screen
    
    private Image menu;
    private Image end;
    private Image scoreboard;
    
    private static ArrayList<Score> highScores;	// local list to store high scores
    private static HighScores hs;		// to write and read new high scores
    
    private JLabel status;		// messages for user
    private JLabel scorebox;	// keeps track of score
    
    // constants 
    public static final int BOARD_WIDTH = 400;
    public static final int BOARD_HEIGHT = 400;
    public static final int MAX_LENGTH = 1600;
    public static final int SEGMENT_SIZE = 10;
    public static final int TIME_INT = 100;
    
    public Model(JLabel status, JLabel scorebox) {
    	setOpaque(true);
        setBackground(Color.BLACK);
        
        Timer timer = new Timer(TIME_INT, new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		boop();		// tick method
        	}
        });
        timer.start();
        
        setFocusable(true);
        
        addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent e) {
        		if (e.getKeyCode() == KeyEvent.VK_LEFT && !snake.getLeft() && !snake.getRight()) {
        			snake.goLeft();		// change snake direction
        		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT && !snake.getLeft() && !snake.getRight()) {
        			snake.goRight();	// change snake direction
        		} else if (e.getKeyCode() == KeyEvent.VK_UP && !snake.getUp() && !snake.getDown()) {
        			snake.goUp();		// change snake direction
        		} else if (e.getKeyCode() == KeyEvent.VK_DOWN && !snake.getUp() && !snake.getDown()) {
        			snake.goDown();		// change snake direction
        		} else if (e.getKeyCode() == KeyEvent.VK_ENTER && state == State.START) {
        			state = State.GAME;	// start game (change from menu)
        		} else if (e.getKeyCode() == KeyEvent.VK_SPACE && state == State.DEAD) {
        			state = State.GAME;	// restart game (change from end screen)
        			reset();
        		} else if (e.getKeyCode() == KeyEvent.VK_SHIFT && state == State.DEAD) {
        			state = State.SCORES;	// display scoreboard (change from end screen)
        			repaint();
        		} else if (e.getKeyCode() == KeyEvent.VK_SPACE && state == State.SCORES) {
        			state = State.GAME;		// restart game (change from scoreboard)
        			reset();
        		}
        	}
        	
        	public void keyReleased(KeyEvent e) {
        		return;
        	}
        });
        
        this.status = status;
        this.scorebox = scorebox;
        state = State.START;	// default set to start menu
        
        try {
			hs = HighScores.make("scores.txt");
		} catch (IOException io) {
			io.printStackTrace();
		}
        
        highScores = hs.getScores();	// get existing list of high scores
    }
    
    public void reset() {
    	snake = new Snake(MAX_LENGTH);	// create new snake
    	candy = new Candy();			// create new candy
    	score = 3;						// reset score
    	alive = true;					
    	
    	status.setText("welcome!");
    	scorebox.setText("");
    	
    	requestFocusInWindow();
    }

    public void setScore(int s) {
    	score = s;
    }
    
    public int getScore() {
        return score;
    }
    
    public boolean isValidUser(String u) {
    	for (int i = 0; i < u.length(); i++) {
    		if (!TextScanner.isLetter(u.charAt(i))) {	// check that every char is a letter
    			return false;
    		}
    	}
    	return true;
    }
    
    public void setState(State s) {
    	state = s;
    }
    
    public State getState() {
    	return state;
    }
    
    public void setStatus(JLabel s) {
    	status = s;
    }
    
    public String getStatus() {
    	return status.getText();
    }
    
    // called by the timer
    public void boop() {
    	if (alive && state == State.GAME) {
        	if (!snake.bumper()) {	// collision has occurred
        		alive = false;		// snake is dead
        		state = State.DEAD;	// switch to end panel
        		status.setText("");	
        		scorebox.setText("final score: " + Integer.toString(score));
        		checkScore();
        		repaint();			// update frame
        	}
        	
        	else {
        		snake.move();		// move the snake one cell
        		snake.eatCandy(candy);	// look for candies to eat
        		if (candy.getEaten()) {	// if candy is eaten
        			if (score >= 30) {	// if score >= 30, then super candies!!
        				candy = new SuperCandy();
        				status.setText("~ super alive and super wiggling ~");
        			} else {			// else normal candies
        				candy = new Candy();
        			}
            		score += candy.getScore();	// update score
        		}
        		
        		scorebox.setText("| score: " + Integer.toString(score));
        		repaint();
        	}
    	}
    }
    
    public void checkScore() {
    	for (int i = 0; i < 4; i++) {			
    		Score sc1 = highScores.get(i);
    		
    		if (score >= sc1.getScore()) {	// if this score is a new record
    			String name = JOptionPane.showInputDialog("new high score!! please enter your name. (letters only)");	// prompt user to enter name
    			name = name.toLowerCase();	// for uniform display and saving
    			
    			if (isValidUser(name)) {
    				highScores.add(new Score(name, score));	// add new record
    				Collections.sort(highScores, Collections.reverseOrder()); 
    				break;									// quit loop
    			} else {
    				name = JOptionPane.showInputDialog("please enter a valid name!!! (letters only)");	// prompt again
    			}    	
    		}
    	}
    	
    	int diff = highScores.size() - 5; // if length is now greater than five
    	for (int i = 5; i < diff; i++) {
    		highScores.remove(i);	// trim to five high scores
    	}
    	
		saveHighScores();	// write scores to file
		state = State.SCORES;	// display scoreboard
    }
    
    public void saveHighScores() {
    	FileWriter writeFile = null;
		BufferedWriter writer = null; 
		
		try {
			writeFile = new FileWriter("scores.txt");
			writer = new BufferedWriter(writeFile);
			
			for (Score sc : highScores) {	// write out each high score to designated file
				if (sc.getScore() == -1) {	// do not write if filler score
					writer.write("");
				} else {
					String un = sc.getUser();
					String s = ((Integer) sc.getScore()).toString();
					writer.write(un + s);     // format: userone99usertwo98userthree97
				}
			}
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();		// close writer
				} catch (IOException io) {
					io.printStackTrace();
				}
			}
		}
    }
    
    public void drawHighScores(Graphics g) {
    	Font fnt = new Font("courier new", Font.BOLD, 14);	
    	g.setFont(fnt);						// set font
    	for (int i = 0; i < 5; i++) {		// draw each string
			Score s = highScores.get(i);
			if (s.getScore() != -1) {
				String p = s.getUser() + ": " + ((Integer) s.getScore()).toString();
				g.setColor(Color.WHITE);
				g.drawString(p, 150, 150 + 37*i);
			}
		}
    }
    
    public void loadImages() {
    	ImageIcon m = new ImageIcon(this.getClass().getResource("/img/menu.png")); 
    	menu = m.getImage();
    	
    	ImageIcon d = new ImageIcon(this.getClass().getResource("/img/dead.png"));
    	end = d.getImage();
    	
    	ImageIcon s = new ImageIcon(this.getClass().getResource("/img/scores.png"));
    	scoreboard = s.getImage();
    }
    
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	loadImages();
    	if (state == State.GAME) {		// if in game
			status.setText("~ alive and wiggling ~");
    		candy.draw(g, this);		// draw candy	
    		snake.draw(g, this);		// draw snake
    	} else if (state == State.START) {	// if in start menu
    		g.drawImage(menu, 0, 0, BOARD_WIDTH, BOARD_HEIGHT, this);
    	} else if (state == State.DEAD) {	// if dead
    		g.drawImage(end, 0, 0, BOARD_WIDTH, BOARD_HEIGHT, this);
    	} else if (state == State.SCORES) {	// if on scoreboard
    		g.drawImage(scoreboard, 0, 0, BOARD_WIDTH, BOARD_HEIGHT, this);
    		drawHighScores(g);				// draw scores
    	}
    }
    
    @Override
    public Dimension getPreferredSize() {
    	return new Dimension(BOARD_WIDTH, BOARD_HEIGHT); 
    }
}