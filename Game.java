/* lstting
 * cis 120 f18
 * hw 09
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game implements Runnable {

	public void run() {
		
		//final Model wiggle = new Model();
		//final ModelPanels bop = new ModelPanels("linda's gummy worm", wiggle);
		
		
		final JFrame frame = new JFrame("linda's gummy worm");
		frame.setLocation(500,200);
		frame.setResizable(false);
		
		// start panel
		final JPanel start_panel = new JPanel();
		frame.add(start_panel, BorderLayout.NORTH);
		final JTextField input = new JTextField(15);
		start_panel.add(input);
		
		// status panel
		final JPanel status_panel = new JPanel();
		frame.add(status_panel, BorderLayout.SOUTH);
		final JLabel status = new JLabel("~ alive and wiggling ~");
		final JLabel scorebox = new JLabel("| score: 0");
			
		status_panel.add(status);
		status_panel.add(scorebox);
		
		final Model wiggle = new Model(status, scorebox);
		
		// game board
		frame.add(wiggle, BorderLayout.NORTH);
				
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		wiggle.reset();
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game());
	}

}
