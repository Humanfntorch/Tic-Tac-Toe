/*****************************
 * Author: Jacob Rogers
 ****************************/
package tictactocked;

//Importing ImageIcon to set the icons for both players.
import javax.swing.ImageIcon;


public class Mechanics
{	
	// Fields
	private  int numMoves = 0;
	private boolean gameWin = false;
	private int p1Score;
	private int p2Score;
    private static int[] setArray = {1, 2, 4, 8, 16, 32, 64, 128, 256};
    private static int[] winArray = {7, 56, 73, 84, 146, 273, 292, 448};
    private static int winScoreX;
    private static int winScoreO;
    private int drawScore = 511;
    private int draws;
    private int rounds;
    private ImageIcon[] iconArray;
	/* Here, we're going to create our private image icon fields for player 1 and player 2. 
	 * These Icons will be displayed on the buttons when the user clicks on them. We can either set the icon in the constructor
	 * like I've done here, or we can set them using our BUTTON object name .setDisabledIcon() method // SEE WINDOW CLASS FOR EXAMPLE (AL METHOD).
	 * We want the image inside of our package src folder with all of our other java files, 
	 * and the image type should be .png, because I can't figure out how to get a .jpg to run with Java. 
	 * To set the image we use getClass().getResource, this says to look at the class source folder
	 * in our case it's our package src folder, and getResource retrieves our specified file call as done below.
	 */
	private ImageIcon defaultX = new ImageIcon(getClass().getResource("resources/X.png"));
	private ImageIcon defaultO = new ImageIcon(getClass().getResource("resources/O.png"));
	private ImageIcon bats = new ImageIcon(getClass().getResource("resources/Batman.png"));
	private ImageIcon jokes = new ImageIcon(getClass().getResource("resources/Joker.png"));
	private ImageIcon bends = new ImageIcon(getClass().getResource("resources/Bender.png"));
	private ImageIcon fry = new ImageIcon(getClass().getResource("resources/Fry.png"));
	private ImageIcon flower = new ImageIcon(getClass().getResource("resources/FlowerO.png"));
	private ImageIcon twigs = new ImageIcon(getClass().getResource("resources/twigs.png"));
	private ImageIcon gears = new ImageIcon(getClass().getResource("resources/Gears.png"));
	private ImageIcon halo = new ImageIcon(getClass().getResource("resources/haloX.png"));
	private ImageIcon orgO = new ImageIcon(getClass().getResource("resources/originalO.png"));
	private ImageIcon orgX = new ImageIcon(getClass().getResource("resources/originalX.png"));
	private ImageIcon sCat = new ImageIcon(getClass().getResource("resources/spaceCat.png"));
	private ImageIcon sDog = new ImageIcon(getClass().getResource("resources/spaceDog.png"));
	private ImageIcon pig = new ImageIcon(getClass().getResource("resources/Piglet.png"));
	private ImageIcon pooh = new ImageIcon(getClass().getResource("resources/Pooh.png"));
    private ImageIcon player1 = orgX;
    private ImageIcon player2 = orgO;
	
    // Methods
    
    /* This method will set all of the image icons
     * into an icon array, and when a index value is passed
     * to the method, it will return the requested image icon.
     * This will be used to set the icons in the iconPanel for customization.
     */
	public ImageIcon setButtonIcon(int index) 
	{	
		iconArray = new ImageIcon[16];
		iconArray[0] = defaultX;
		iconArray[1] = defaultO;
		iconArray[2] = orgX;
		iconArray[3] = orgO;
		iconArray[4] = bends;
		iconArray[5] = fry;
		iconArray[6] = twigs;
		iconArray[7] = flower;
		iconArray[8] = halo;
		iconArray[9] = gears;
		iconArray[10] = sCat;
		iconArray[11] = sDog;
		iconArray[12] = bats;
		iconArray[13] = jokes;
		iconArray[14] = pooh;
		iconArray[15] = pig;
		return iconArray[index];
	}
	
	/* These two methods will allow the users to 
	 * choose their own icon! We set the default images in the
	 * fieds for player1 and player2, but if the user decides
	 * to customize their icon, we manipulate the icon by passing
	 * the index the user chooses from the iconPanel and pass it
	 * back to the iconArray and assign the players icon to the respective image.
	 * Clever eh?
	 */
	public void setPlayer1Icon(int i) 
	{
		this.player1 = iconArray[i];
	}
	
	public void setPlayer2Icon(int i) 
	{
		this.player2 = iconArray[i];
	}
	
	// This method increments the field numMove by one when called. Ideally to track the method getTurn().
	public int numMoves()
	{
		return ++numMoves;
	}
	
	// This method will allow us to determine which players turn is next. It utilizes the modulus
	// and our method for numMoves(). Depending on our value for numMoves we can find whose turn is next
	// because one should always be even, while the other is odd. So % 2 == 0 is always valid.
	public ImageIcon getTurn() 
	{
		if(numMoves % 2 == 0) 
		{
			return player1;
		}
		else
			return player2;
	}
	
    /* This method accepts an int value index that corresponds to a for loop
	 * created in the Gui class. It will allow us to assign the appropriate value
	 * for the button that was clicked to the winScores for player1 and player2. These
	 * scores will be used in a comparison against a winArray using bitwise operators.  
	 */
	public void playerScoreArray(int index)
	{
		if(getTurn().equals(player1))
	    {
			winScoreX += setArray[index];
	    }
	  	
		if(getTurn().equals(player2))
	    {
	          winScoreO += setArray[index];
	    }
	}
		
	    /* This method will check the win/draw condition for the game, return the gameWin condition,
	     * increment the win/draw score, and round count. We implemented bitwise operators that checks
	     * the binary placement for each numerical value assigned to the winScore for x, and o.   
	     */
	public boolean checkWin() 
	{	
		for(int i = 0; i < winArray.length; i++)
		{
			if((winScoreX & winArray[i]) == winArray[i])
			{	
				this.rounds += 1;
				this.p1Score += 1;
               	gameWin = true;  	
			}
			   
			else if((winScoreO & winArray[i]) == winArray[i])
			{	
				this.rounds += 1;
				this.p2Score += 1;  
			   	gameWin = true;
			}
		}
			
		if((winScoreX + winScoreO) == drawScore)
	    {  
			this.rounds += 1;
	        this.draws += 1;
	        gameWin = true;
	    }
		return gameWin;
	}
	
	// returning the score for player1
	public int getScore1()
    {
		return this.p1Score;
    }
      
	// returning the score for player2
	public int getScore2()
    {
		return this.p2Score;
    }
	
    // returning the number of draws  
	public int getDraws() 
    {
		return this.draws;
    }
	
	// return the number of rounds
	public int getRounds() 
	{
		return this.rounds;
	}
	
	/* This method will reset the players winScore values, the numMoves value, 
	 * and set the gameWin as false when its called. This is for use when the users
	 * want to play another match. 
	 */
	public void resetGame() 
	{  
		winScoreX = 0;
        winScoreO = 0;
        numMoves = 0;
        gameWin = false;
	}
}