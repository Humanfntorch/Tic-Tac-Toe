/*******************************
 * Author: Jacob Rogers
*******************************/
package tictactocked;

// Imports
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
// Importing our design layout and button listeners and events
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
// importing our buttons, frame, and panel.
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.util.Scanner;

public class Gui implements ActionListener
{	
	// Fields
	private JFrame frame; // Private Frame
	private JPanel menuPanel;
	private JPanel gamePanel; // Private game panel
	private JPanel boardPanel;
	private JPanel mainPanel;
	private JPanel functionPanel;
	private JPanel creditsPanel;
	private JPanel imagePanel;
	private JPanel iconPanel;
	private JPanel functionPanel2; 
	// Creating our constant String fields for our cardlayout manager
	private final static String GAMEPANEL = "Game Board";
	private final static String MENUPANEL = "Main Menu";
	private final static String CREDITPANEL = "Credits";
	private final static String ICONPANEL = "Set image icon";
	private JButton[] buttons = new JButton[9]; // Creating a private array of JButtons
	private JButton start;
	private JButton start2;
	private JButton customization;
	private JButton customizeIcon;
	private JButton credits;
	private JButton rematch;
	private JButton menu;
	private JButton menu2;
	private JButton menu3;
	private JButton exit;
	private Mechanics control = new Mechanics(); // Control object from our mechanics class. Used to call all game methods.
	// Declaring our constraint object to manage our gridbaglayout
	private GridBagConstraints c;
	// declaring our buffured image for our main menu panels background
	private BufferedImage backgroundIMG = null;
	// declaring a font variable
	private Font font;
	private JTextArea gameText;
	private JTextArea creditText;	
	private CardLayout cardControl;
	private String player1;
	private String player2;
	private Scanner input;
	private JButton[] iconButtons;
	private int controlVariable;
	// Constructor
	public Gui()
	{	// call our frame method to start our game.
		frame();
	}	
	
	
	// Methods
	
	public JFrame frame()
	{
		/* Instantiate our frame that will hold the mainPanel and create our game screen.
		 * We set the default close operation as exit on close, so that the program will stop running if the user clicks the exit
		 * button at the top of the frame. We use the pack method for the JFrame, because this will automatically size everything that we
		 * place inside the jframe, and size the frame itself accordingly. Set visible as true, because we want the user to see our beautiful project.
		 */

		frame = new JFrame("Tick Tack Toe!"); // instantiating frame with a cute title
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200, 800); // This makes it so we have to full screen our window, because it's resizing everything to fit properly.
		frame.setVisible(true); // We certainly want to see what we're playing 
		
		/* Instantiate our mainPanel and set a new border layout as CardLayout. CardLayout allows us to tabulate our panels like a web browser allows us
		 * to switch between seperate tabs in our window. All other panels are set in their own methods, and will return the final panel when the method is
		 * called, and so we can add the panels to our main panel here. Constant names are used to specify the panel(tab), and allows us to change between
		 * the tabs on call. The default tab will be the main menu panel, and using the respective buttons, we will call a change of tab to the compiler to
		 * swap between our panels. The variable cardControl is our object that allows us to call for a tab switch. We have to cast the return of the getLayout
		 * method for our mainPanel to a card layout, because we need all layouts to be of type card if we want to operate on them with the control object. 
		 */
		
		mainPanel = new JPanel(new CardLayout());
		mainPanel.add(gamePanel(), GAMEPANEL);
		mainPanel.add(menuPanel(), MENUPANEL);
		mainPanel.add(creditsPanel(), CREDITPANEL);
		mainPanel.add(iconPanel(), ICONPANEL); 
		frame.add(mainPanel);
		
		cardControl = (CardLayout) (mainPanel.getLayout());
		cardControl.show(mainPanel, MENUPANEL);
		return frame;
	}
	
	
	/* This method will return our game panel which will contain our buttons for the game
	 * and the text area that will display game information for the user. I tried a few different ways to set
	 * the gamePanel up just using gridbaglayout, but ran into issues while clicking the buttons and having the
	 * icon images increase in size. To combat this issue, we will place our game buttons in a separate
	 * board panel using a simple 3x3 gridlayout, and placing our text area along with the rematch and menu
	 * button in a separate function panel, and just add the board, and function panel to the single gamePanel.
	 */
	public JPanel gamePanel() 
	{
		boardPanel = new JPanel(); // instantiating board panel, will contain all game buttons
		boardPanel.setLayout(new GridLayout(3,3)); // use gridlayout with 3 rows and 3 columns
		functionPanel = new JPanel(new GridBagLayout()); // function panel for rematch, menu buttons and text area. Use gridBaglayout 
		gamePanel = new JPanel(new GridBagLayout()); // Game Panel will contain function panel and board panel. Use griBagLayout
		c = new GridBagConstraints(); // Initializing our constraint
		font = new Font("Broadway", Font.PLAIN, 25); // Create a font for our text area and function buttons
	
		// Create function buttons and add actionListeners.
		rematch = new JButton("Rematch");
		menu = new JButton("Menu");
		rematch.addActionListener(this);
		menu.addActionListener(this);
		
		// set font on function buttons
		rematch.setFont(font);
		menu.setFont(font);
		
		// We want to set the rematch button false until the game is won or until the 
		// game results in a draw. Once game == true, rematch will be enabled.
		rematch.setEnabled(false);

		// Setting the constraints for the buttons and add them to the function panel
		c.gridy = 5; // Setting the y position at the bottom of the panel
		c.gridx = 4; // Setting x position at the bottom right corner
		c.weighty = .5; // Providing vertical weight to the buttons
		c.gridheight = 1; // Setting height of the buttons
		c.fill = GridBagConstraints.BOTH; // fill empty space
		functionPanel.add(rematch, c); // add rematch
		c.gridx = 5; // move the menu button to the right of the rematch button
		functionPanel.add(menu, c); // add menu

		// Setting the constraints for the text area and adding it to the function panel
		c.anchor = GridBagConstraints.EAST; // Lock it in place on the east
		c.gridx = 4;  // x position
		c.gridy = 0; // y position
		c.weighty = 5; // Give text area a lot of vertical weight.
		c.gridheight = 3; // Setting the height of the text area to span 3 cells
		c.gridwidth = 2; // Allowing 2 cells of width for the text area and buttons (Mainly to fit the buttons in)
		c.fill = GridBagConstraints.BOTH; // Fill the text area from cell 0 to cell 3
		functionPanel.add(gameText(), c); // add text area
		
		// Add function panel to game panel
		gamePanel.add(functionPanel, c);
	
		// Create a font for game buttons (Increase font size)
		Font fontGameBtns = new Font("Broadway", Font.PLAIN, 60);
		
		// Running a for loop for our buttons.
		for (int i = 0; i < 9; i++) 
		{	
			buttons[i] = new JButton(); // instantiate buttons
			buttons[i].addActionListener(this); // add action listener
			buttons[i].setFont(fontGameBtns); // Setting font on game buttons. 
			boardPanel.add(buttons[i]); // add buttons to board panel
		}
		
		// Set the constraints for our boardPanel
		c.anchor = GridBagConstraints.WEST; // Lock it in on the west side
		c.gridx = 0; // x position
		c.gridy = 0; // y position.
	    c.gridwidth = 1; // Setting 3 cells for the width of the board
		c.gridheight = 5; // Setting 5 cells for the height
		c.weighty = 0; // No vertical weight
		c.weightx = .1; // Give it 10% of the horizontal weight
		c.fill = GridBagConstraints.BOTH; // Fill both vertical and horizontal space
		gamePanel.add(boardPanel, c); // add board panel to the game panel/
		return gamePanel;
	}
	
	
	public JTextArea gameText() 
	{
		font = new Font("Broadway", Font.PLAIN, 30); // Creating a new font for our text area
		gameText = new JTextArea(); // instantiate our game text area
		gameText.setFont(font); // Setting the font for our text area
		gameText.setRows(40); // set the rows for the text area
		gameText.setColumns(25); // set the columns for the text area
		gameText.setLineWrap(true); // Allowing the text to start a new line if the line gets too long.
		gameText.setEditable(false); // Making it so the user can't change the text in the display.
		
		/* We append the string.format to gameText using our getPlayer names method, and the methods
		 * built in mechanics that return the players scores, draws, and total games played. 
		 * There is a strange bug with the player 1 and player 2 score methods in the mechanics
		 * class, where scoreP1 seems to be player 2's score, and scoreP2 appears to be player 1's
		 * score. We can't find the bug, and instead of scrapping the idea, we simply switch the score
		 * methods! =)
		 */
		gameText.append(String.format("%s \n  vs \n%s \n\n\n", getPlayer1(), getPlayer2()));
		gameText.append(String.format("%s \nScore: %d \n\n\n", getPlayer1(), control.getScore2()));
		gameText.append(String.format("%s \nScore: %d \n\n\n", getPlayer2(), control.getScore1()));
		gameText.append(String.format("Stalemates: %d \n\n\n", control.getDraws()));
		gameText.append(String.format("Total Games \nPlayed: %d", control.getRounds()));
		return gameText;
	}
	
	
	/* This method returns our main menu panel which contains an overrided paint component that allows us to draw 
	 * a background image, and also the buttons for the user to navigate through the other panels in our game.
	 */
	@SuppressWarnings("serial")
	public JPanel menuPanel() 
	{	
		/* Here we set our background image using ImageIO.read to assign our background image to our buffered image
		 * variable. We need to run a try / catch exception, just in case the compiler isn't able to find the
		 * associated image and returns a null value. We have the image in our src folder, and so our exception 
		 * shouldn't be necessary.
		 */
		try
		{
			backgroundIMG = ImageIO.read(getClass().getResource("resources/TTT_BackGround.png"));
		}catch(IOException e) 
		{
				
		}
		
		/* To add a background image for the main menu on the jpanel, we had a couple options
		 * none of them good, and so without pushing the boundaries of this intro to object oriented
		 * course, I chose to override a method paintComponent, so that we could draw our own
		 * image type on the background of our JPanel, without it leaving a ton of empty space.
		 * The other choices sucked, like using a JLabel, which wouldn't resize the image, and would cover
		 * a lot of the other objects in our container, and the other, inheritance and extending jpanel, is
		 * way too complicated to explain on my own. Lesser of evils I suppose...
		 * 
		 * This is known as ANONYMOUS CLASS inheritance.
		 */
		menuPanel = new JPanel() 
		{
			public void paintComponent(Graphics g) 
			{	
				super.paintComponent(g); // Instantiating our parameter g as our paint component.
				g.drawImage(backgroundIMG, 0, 0, menuPanel.getWidth(), menuPanel.getHeight(), this); 
				/* Calling our painheight thatt component to draw our background image inside our jpanel with a set width
				 * and  corresponds to the size of the jpanel. The this statement corresponds to the observer argument,
				 * and this means that the panel is notified every time the image is repainted, resized or manipulated in any way.
				 */
			}
		};
		
		// Setting up our grid bag layout so we can position our buttons to navigate through the menu.
		menuPanel.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		
		// Instantiating font to use in our buttons
		font = new Font("Broadway", Font.PLAIN, 40);
		
		// Instantiating our buttons for the menu.
		start = new JButton("Play Game");
		customization = new JButton("Customize Player Names");
		credits = new JButton("Credits");
		exit = new JButton("Exit");
		customizeIcon = new JButton("Customize Icon");
		
		// Changing the font types of each button
		start.setFont(font);
		customization.setFont(font);
		credits.setFont(font);
		exit.setFont(font);
		customizeIcon.setFont(font);
		
		// Adding action listeners to the buttons
		start.addActionListener(this);
		customization.addActionListener(this);
		credits.addActionListener(this);
		exit.addActionListener(this);
		customizeIcon.addActionListener(this);
		
		// Setting our constraints for the buttons and adding them to our menuPanel
		c.anchor = GridBagConstraints.CENTER; // Lock in to the center of the panel
		c.gridwidth = 5; // 5 cells horizontal space
		c.fill = GridBagConstraints.HORIZONTAL; // Fill all horizontal space
		menuPanel.add(start, c); // add start
		c.gridy = 1; // Setting the customization button below the start button.
		menuPanel.add(customization, c); // add customization
		c.gridy = 2; // set below customization
		menuPanel.add(customizeIcon, c); // add customize icon
		c.gridy = 3; // set below customize icon
		menuPanel.add(credits, c); // add credits
		c.gridy = 4; // set below credits
		menuPanel.add(exit, c); // add exit
		
		/* The slickest way that I could change the size of the buttons, was to actually increase the size of the FONT for the buttons,
		 * and this changed the size of buttons using Java's inherent preferred size algorithm, rather than having us manually input a size
		 * for the buttons. I'm quite proud of this discovery =).
		 */
		return menuPanel;
	}
	
	
	/* This method will return our credits panel. We will be adding a text area to contain the text 
	 * for the group members to write about themselves (if they wish).This method will also contain 
	 * a font variable, to add flare within the text area. a Menu button will be provided below the text area 
	 * so the players can return to the game menu.
	 */
	public JPanel creditsPanel() 
	{	
		// Instantiate our credit panel
		this.creditsPanel = new JPanel();
		
		// Set GridBagLayout for panel
		creditsPanel.setLayout(new GridBagLayout());
		
		// Instantiate our text area, buttons and font.
		creditText = new JTextArea();
		font = new Font("Broadway", Font.PLAIN, 40);
		menu2 = new JButton("Main menu");		
		menu2.addActionListener(this); // Adding action listener to menu
		
		// Set the font for buttons and text area
		menu2.setFont(font);
		creditText.setFont(font);
		
		// Set size and default operations of text area
		creditText.setLineWrap(true);
		creditText.setEditable(false);
		creditText.setRows(20);
		creditText.setColumns(10);
		
		// Append text from project creators
		creditText.append("\nProject Creator: \n\n");
		creditText.append("Jake - Lead Programmer, GUI Junky, Project Design Lead, Mechanics Logic, and Game Programmer.  \n\n");
		creditText.append("\nShout outs: \n\n");
		creditText.append("Thank you Karen for opening up the world of Java, and allowing me to "
				+ "make this game \nthe way i envisioned it to be.");
		
		// Set constraints for text area
		c.anchor = GridBagConstraints.CENTER; // Lock it in the center of the panel
		c.gridx = 0; // origin position
		c.gridy = 0;
		c.gridheight = 4; // 4 vertical cells in the panel
		c.gridwidth = 2; // 2 horizontal cells in the panel
		c.weighty = .01; // 1% vertical weight.
		c.weightx = .1; // 10% horizontal weight
		c.fill = GridBagConstraints.BOTH; // Fill empty space, both horizontal and vertical.
		creditsPanel.add(creditText, c); // add text area to panel
		
		// Set constraints for the button
		c.gridx = 0; // initial x position
		c.gridy = 5; // set at the bottom of the panel
		c.gridheight = 1; // Allow 1 cell for the height.
		c.fill = GridBagConstraints.BOTH; // Fill empty space
		creditsPanel.add(menu2, c); // add menu to panel
		return creditsPanel;
	}
	
	
	// A set method for custom player names	
	public void customNames(String player1, String player2) 
	{
			this.player1 = player1;
			this.player2 = player2;
	}
	
	
	// Getter for player1
	public String getPlayer1() 
	{	
		if(player1 == null) // Set a default name in case the user doesn't customize their own. 
		{
			this.player1 = "Player X";
		}
		return player1;
	}
	
	
	// Getter for player2
	public String getPlayer2() 
	{	
		if(player2 == null) // Set default name if user doesn't customize their own
		{
			this.player2 = "Player O";
		}
		return player2;
	}
	
	
	public JPanel iconPanel() 
	{
		imagePanel = new JPanel(); // instantiating board panel, will contain all images on the  buttons
		imagePanel.setLayout(new GridLayout(4,4)); // use gridlayout with 3 rows and 3 columns
		functionPanel2 = new JPanel(new GridBagLayout()); // function panel for rematch, menu buttons and text area. Use gridBaglayout 
		iconPanel = new JPanel(new GridBagLayout()); // Game Panel will contain function panel and board panel. Use griBagLayout
		c = new GridBagConstraints(); // Initializing our constraint
		font = new Font("Broadway", Font.PLAIN, 25); // Create a font for our text area and function buttons
		
		// Instantiate iconText area and set parameters
		JTextArea iconText = new JTextArea();
		iconText.setEditable(false);
		iconText.setLineWrap(true);
		iconText.setRows(20);
		iconText.setColumns(30);
		iconText.setFont(font);
		
		// Set the text for the text area
		iconText.append("Click the image you \nwould like to play \nwith.\n\n"
				+ "The first marker \nchoice will be \nassigned to player \none, "
				+ "and the second \nwill be assigned to \nplayer two. \n\n");
		iconText.append("If you choose not to \npick a custom image,\nyour marker will \n"
				+ "default to the first \nor second icon in the \nlist.\n\n");
		iconText.append( "When you're \nfinished you can \nstart the game by \npressing "
				+ "Start game, \nor jump to the menu \nby pressing menu.\n");
		
		
		// Create function buttons and add actionListeners.
		start2 = new JButton("Play Game");
		menu3 = new JButton("Menu");
		start2.addActionListener(this);
		menu3.addActionListener(this);
		
		// set font on function buttons
		start2.setFont(font);
		menu3.setFont(font);
		

		// Setting the constraints for the buttons and add them to the function panel
		c.gridy = 5; // Setting the y position at the bottom of the panel
		c.gridx = 4; // Setting x position at the bottom right corner
		c.weighty = .5; // Providing vertical weight to the buttons
		c.gridheight = 1; // Setting height of the buttons
		c.fill = GridBagConstraints.BOTH; // fill empty space
		functionPanel2.add(start2, c); // add rematch
		c.gridx = 5; // move the menu button to the right of the rematch button
		functionPanel2.add(menu3, c); // add menu

		// Setting the constraints for the text area and adding it to the function panel
		c.anchor = GridBagConstraints.EAST; // Lock it in place on the east
		c.gridx = 4;  // x position
		c.gridy = 0; // y position
		c.weighty = 5; // Give text area a lot of vertical weight.
		c.gridheight = 3; // Setting the height of the text area to span 3 cells
		c.gridwidth = 2; // Allowing 2 cells of width for the text area and buttons (Mainly to fit the buttons in)
		c.fill = GridBagConstraints.BOTH; // Fill the text area from cell 0 to cell 3
		functionPanel2.add(iconText, c); // add text area
		
		// Add function panel to game panel
		iconPanel.add(functionPanel2, c);
	
		iconButtons = new JButton[16];
		// Running a for loop for our buttons.
		for (int i = 0; i < iconButtons.length; i++) 
		{	
			iconButtons[i] = new JButton(); // instantiate buttons
			iconButtons[i].addActionListener(this); // add action listener
			iconButtons[i].setIcon(control.setButtonIcon(i));
			imagePanel.add(iconButtons[i]); // add buttons to board panel
		}
		
		// Set the constraints for our imagePanel
		c.anchor = GridBagConstraints.WEST; // Lock it in on the west side
		c.gridx = 0; // x position
		c.gridy = 0; // y position.
	    c.gridwidth = 1; // Setting 3 cells for the width of the board
		c.gridheight = 5; // Setting 5 cells for the height
		c.weighty = 0; // No vertical weight
		c.weightx = .1; // Give it 10% of the horizontal weight
		c.fill = GridBagConstraints.BOTH; // Fill both vertical and horizontal space
		iconPanel.add(imagePanel, c); // add image panel to the icon panel
		return iconPanel;
	}
	
	
	/* This method will allow the user to enter the names for player1 and player2,
	 * and then will run through a do-while loop to find where the user would like to enter
	 * back into the games interface using a switch/ case statement
	 */
	public void customizePanel()
	{	
		/* Instead of running through the customization in a panel, we decided
		 * to keep this method simple, and to do this, we will close the frame for the game
		 * and run through a series of prompts in the console window, where the user can
		 * enter their data, and then choose where they'd like to continue the game.
		 * We will call frame.dispose() to close the Gui application, and when the user chooses
		 * what screen they'd to like to enter, we'll simply call our frame() method to open a
		 * new window, and using our cardControl manipulator we'll flip to the tab the user requested.
		 */
		frame.dispose();
		
		// instantiate scanner and create sentinel variable for a menu.
		input = new Scanner(System.in);
		int userChoice;
		
		// Ask the user to enter the names for user1 and user2 and assign the input to our local variables.
		System.out.print("Please enter the name for the first player: ");
		String user1 = input.nextLine();
		System.out.println();
		System.out.print("Please enter the name for the second player: ");
		String user2 = input.nextLine();

		
		// Call our customName method and pass our local variables as the arguments
		customNames(user1, user2);
		
		/* Prompt the user with a menu of choices on where they'd like
		 * to continue their game. Choices include: Jump to the main menu, Start the game, redo 
		 * the customization, or exit. These will be executed with a switch/case statement.  
		 */
		do 
		{
			System.out.println();
			System.out.println("Where would you like to continue your game?");
			System.out.print("Input 1 to start the game \n"
							 + "Input 2 to jump to the main menu \n"
							 + "Input 3 to change user names \n"
							 + "Input 4 to customize player markers \n"
							 + "Input 5 to quit the game. \n"
							 + "Your choice: ");
			userChoice = input.nextInt();
			
			/* Call the frame() method to open our game Gui, and use cardControl to
			 * flip to the tab the user chooses. We create a sentinel value for our do/ while
			 * loop in the case the user enters an invalid input. We could call the custmizePanel()
			 * in this case, but that would require the user enter their names again, and that could
			 * potentially be annoying. Our sentinel value is set to quit if it equals 20, because
			 * this value should be safe from the usual input error. 
			 */
			switch(userChoice) 
			{
				case(1): // Starts the game
					frame();
					cardControl.show(mainPanel, GAMEPANEL);
					userChoice = 20;
					break;
				case(2): // Jumps to the game menu
					frame();
					cardControl.show(mainPanel, MENUPANEL);
					userChoice = 20;
					break;
				case(3): // Restarts the customization process
					customizePanel();
					userChoice = 20;
					break;
				case(4): // Opens the customize icon panel
					frame();
					cardControl.show(mainPanel, ICONPANEL);
					userChoice = 20;
					break;
				case(5):
					System.out.println("Thank you for playing!");
					frame.dispose();
					break;
				default: // Invalid prompt
					System.out.println("Sorry, we don't recognize that choice. \n"
									 + "Please choose an input from the list.");
			}
			System.out.println();
		}while(userChoice != 20);	
	}		
			
			
	/* To simplify the process of setting up a new game board we'll be creating a reset method. This method will run through
	 * all of the data the buttons have accrued (set enabled(true), iconimage(null), and reset any other
	 * method, or field that's needed.
	 */
	public void reset() 
	{	// for each loop to reset all button information.
		for(JButton el : buttons) 
		{
			el.setEnabled(true); // Allows the buttons to be clicked
			el.setIcon(null); // Erases icon image
			el.setDisabledIcon(null); // Erases icon image
		}	
	}
	
	
	/* We override this method, because we want to take control of this method by telling the compiler to send us
	 * all of the private information from the class it comes from, and notifying the compiler that we'll take care
	 * of all of the details on our own.
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{	
		/* Use the action listeners added to each button as a conditional check to swap between the corresponding tabs 
		 * in our main panel. When the button is pressed it sends a signal implicity as an argument to this method, 
		 * and e gets assigned that signal. We can use this data to search for the button that was pressed with the getSource
		 * method. We use the equal() method as our equality operator, due to coding standards, and because it has a better algorithm
		 * for comparing reference types. It searches for the spot in memory where the objects data is stored, and compares the objects with this data.
		 * This data is known as 'HashCode'. 
		 */
		
		// Start button - change to game panel
		if(e.getSource().equals(start) || e.getSource().equals(start2)) 
		{
			cardControl.show(mainPanel, GAMEPANEL);
		}
		
		// Menu button - change to menu panel
		if(e.getSource().equals(menu) || e.getSource().equals(menu2) || e.getSource().equals(menu3))
		{
			cardControl.show(mainPanel, MENUPANEL);
		}
		
		/* Rematch button - check gameWin/ if true: run resetGame()
		 * To update the games text area, we need to refresh the frame that
		 * all of our panels are in, and so we throw the old panel away (close it),
		 * by running dispose, and then call for a repaint on the frame method. This
		 * will create a new updated frame that refreshes the information inside the text area.
		 * We then use card control to flip back to the game tab.
		 */
		if(e.getSource().equals(rematch))
		{
			control.resetGame();
			frame.dispose(); // Close old frame
			frame().repaint(); // Repaint (Create a new updated version of frame())
			cardControl.show(mainPanel, GAMEPANEL); // change tab
			rematch.setEnabled(false); // Set rematch to false after new frame is updated.
		}
		
		// Customization button - run our customization loop when user inputs their name 
		// they can choose to open menu, start the game, redo customization or quit the game. (switch / case statement)
		if(e.getSource().equals(customization)) 
		{
			customizePanel();
		}
		
		// CustomizeIcon button - flip to the icon tab
		if(e.getSource().equals(customizeIcon)) 
		{	
			cardControl.show(mainPanel, ICONPANEL);
		}
			
		/* This for loop will run through the icon buttons inside
		 * the icon panel, and if the user clicks on the button,
		 * we'll pass that index for the button to the set player
		 * icon method in mechanics to assign that icon for the player
		 * and we'll set some data on the buttons that allows
		 * the user to see which icon they're assigned to. We could
		 * try to erase the previous information, but that would be a
		 * little tricky, and I'm not in the mood for that right now. 
		 * We're using Magenta, because out of our color list, this
		 * shows up best on the variety of images we have to choose from.
		 * Other colors are too difficult to see on some images. 
		 */
		for(int i = 0; i < iconButtons.length; i++) 
		{	
			Font font = new Font("Broadway", Font.PLAIN, 45);
			if(e.getSource().equals(iconButtons[i])) 
			{
				if(controlVariable % 2 == 0) 
				{
					control.setPlayer1Icon(i);
					iconButtons[i].setVerticalTextPosition(JButton.CENTER);
					iconButtons[i].setHorizontalTextPosition(JButton.CENTER);
					iconButtons[i].setFont(font);
					iconButtons[i].setForeground(Color.MAGENTA);
					iconButtons[i].setText("Player 1");
					++controlVariable;
				}
				else if(controlVariable % 2 != 0)
				{
					control.setPlayer2Icon(i);
					iconButtons[i].setVerticalTextPosition(JButton.CENTER);
					iconButtons[i].setHorizontalTextPosition(JButton.CENTER);
					iconButtons[i].setFont(font);
					iconButtons[i].setForeground(Color.MAGENTA);
					iconButtons[i].setText("Player 2");
					++controlVariable;
				}
			}	
		}
		
		
		// Credits button - change to credits panel
		if(e.getSource().equals(credits)) 
		{
			cardControl.show(mainPanel, CREDITPANEL);
		}
		
		// Exit button - dispose of window and all objects inside
		if(e.getSource().equals(exit)) 
		{
			frame.dispose();
		}
			
		/* Checking if any of the game buttons have been pressed. If so, run through a for
		 * loop to find the button, change the icon for the button, disable the button
		 * in the current game, and pass data to the mechanics class to keep track of game information.
		 */
		for(int i = 0; i < 9; i++) 
		{	
			if(e.getSource().equals(buttons[i])) 
			{	/* This is the method where we tell Java how we want the buttons to react when
				 * the bell on the button rings when it's clicked. The first two methods set the icon for
				 * when the user clicks the button. We have to set the default image icon first, and then the
				 * disabled icon, or else our image on the button will appear gray, because we disable the button
				 * in the third method call. We disable the buttons so that it can't be pressed again by the other player
				 * or by the same player. We call playerScoreArray(i) so we can keep track of which button is pressed. 
				 * The if statement is if the game condition is won and if it is, we erase all buttons data, remove
				 * the action listeners, and display a message to the players informing the game has ended.
				 */
				buttons[i].setIcon(control.getTurn()); // Set the image on the button
				buttons[i].setDisabledIcon(control.getTurn()); // Set the disabled image for the button
				buttons[i].setEnabled(false); // Stop the button from being used again
				control.numMoves(); // Update the mechanics move counter.
				control.playerScoreArray(i); // Pass the buttons index value to set the winScore for the player (Mechanics)
				
				if (control.checkWin() == true) 
				{	
					for(JButton el : buttons) 
					{
						el.setIcon(null); // Erase buttons image
						el.setDisabledIcon(null); // Erase buttons disabled image
						el.setEnabled(true); // This allows the button to be seen (with color)
						el.removeActionListener(this); // Stop the buttons from sending the compiler signals/
					}
					// Set the text on the buttons when the game is over
					buttons[1].setText("Game");
					buttons[4].setText("Over!");
					buttons[6].setText("Wanna");
					buttons[7].setText("Play");
					buttons[8].setText("Again?");
					
					rematch.setEnabled(true); // Allow rematch to be pressed 
					control.resetGame(); // Reset the fields in Mechanics for a new game
				}
			}
		}
	}
}