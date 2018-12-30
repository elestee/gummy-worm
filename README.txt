/* lstting
 * cis 120 f18
 * hw 09
*/

=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: lstting
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D arrays: snake segment positions
  		
  		I modeled the snake's position as integers stored in a 2D array of
  		dimension l (max length of snake) by 2. The first element of array[i]
  		would be the x position of the ith segment of the snake, and the
  		second element of array[i] would be the y position of the yth segment
  		of the snake. 

  2. dynamic dispatch: candy types

		In order to make the game more difficult as it progresses, I chose to
		make different types of candy for the snake to eat. When the user's score
		reached a certain number, candies of a different appearance and higher
		point values. 

  3. testable components: all new classes, game model
  
  		Many methods within the Model class were testable (and indeed needed to
  		be tested to ensure that they were working as expected), as well as 
  		some of the basic methods within the new classes I wrote. 

  4. file i/o: reading and saving high scores

		I wrote my own reader to efficiently parse bits of information from
		a txt file with a specified format (that I determined in how high scores
		were written to that file in Model). Those scores and usernames were then
		read from the file to be printed on the scoreboard in the game. 

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

	Candy: private score, x position, y position, boolean eaten, image
	
		The Candy class is the object class for what the snake needs to hit 
		(or collide with) to increase its score. 
		
		SuperCandy: This subclass of Candy has a different appearance and
					higher point values. 
	
	Game: 
	
		This class set up the general appearance and layout of elements as
		determined in Model.
	
	HighScores: private ArrayList of Score, TextScanner
	
		Used to create an ArrayList of high scores by reading from a file
		using the TextScanner class. 
	
	Model:
	
		Takes care of a bulk of the backend methods. Initializes new snakes,
		candies, saves a record of high scores, saves a user's current score,
		writes out new high scores to a txt file, and provides some of the
		graphics for the game's user interface.
	
	Score: private username and score
	
		Used to keep track of highscores in an efficient and compact manner. 
		Since each int score corresponds to a username, this class makes it easier
		for high scores to be read and written properly. 
	
	Snake: private length, 2D array for position, booleans for all 4 directions
	
		The Snake class is the object class for the main character within 
		the game. The length of the snake determines the score of the user
		currently playing. In order to paint the snake on the screen, there
		must be a way to store and update the positions of each individual
		segment of the snake. Additionally, since the user can change the 
		direction of the snake with keystrokes, booleans are used to ensure 
		that the changes are made and legal. 
	
	State (enum): START (start menu), GAME (during gameplay), 
				  DEAD (options to restart or see scores), SCORES (scoreboard)
	
		This enum is used to keep track of the game's state, so as to determine
		what elements should be displayed on screen. 

	TextScanner:
	
		Used to read text formatted in a user99 format (username in letters
		followed by an int of the user's score). 

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

	I had some trouble implementing my File I/O for handling the model's high scores.
	I also had some trouble with some of the Java Swing components that allowed for 
	user input (i.e. how to properly parse the user input and use it properly). 

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

	I think there is decent separation of functionality. I do not think any of my
	private states have been exposed to the user. 
	
	If given the chance to refactor, I think I definitely would. More time to sit with
	and consider the design of a program would always be beneficial, and would
	probably give me time to have a deeper understanding of some of the elements
	in this game and how they interact with one another. 

========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
  
  	JavaDocs on JOptionPane, ArrayList, File I/O, JLabel, Writer, Reader
  	
