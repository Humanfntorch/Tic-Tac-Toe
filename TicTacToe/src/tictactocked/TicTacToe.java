/***************************
 * Author: Jacob Rogers
**************************/
package tictactocked;

public class TicTacToe
{
	// Methods
   public static void main(String[] args) 
   {	
	   playGame();
   }

   // This method starts the game
   public static void playGame()
   {
	   new Gui();   
   }
   
   // This method will explain how to play Tic Tac Toe
   public static void rules()
   {
	   System.out.println("Game rules: \n");
	   System.out.println("Tic Tac Toe is a simple yet fun two player game. \n"
	   		+ "We start the game with a 3x3 grid, where the players take turns marking \n"
	   		+ "an empty space with an X if they're going first, or an O if they're going second. \n\n"
	   		+ "The player to succeed in placing three consecutive marks \n"
	   		+ "in a horizontal, vertical, or diagonal row first wins the game.");
	   System.out.println("");
	   
	   System.out.println("General strategies: \n");
	   System.out.println("Marking your first sign in the middle of the grid or in one of the corners \n"
	   		+ "of the game board will help to raise your chances of winning. \n");
	   System.out.println("Be sure to block your opponent's next move when they obtain two marks \n"
	   		+ "in a line, and try to place traps that your opponent will play into. \n");
	   System.out.println("Tic Tac Toe games will often end in a stalemate, where neither player \n"
	   		+ "can find a winning line, but trickiness can come along way. \n"
	   		+ "Win, lose, or stalement... the most important thing is to have fun!");
	   System.out.println("\n");
   }
   
   // This method will explain how to use our game
   public static void gameTutorial()
   {
	   System.out.println("To begin our game, all you need to do is call run() inside the main method. \n"
	   		+ "A new window will be created displaying the game menu, where you can \n"
	   		+ "start a game, customize your names, view the creators of the game, \n"
	   		+ "or quit the application. \n");
	   
	   	System.out.println("When you start the game, a grid of 9 buttons are displayed, \n"
	   		+ "along with a text area that will display your scores, draws and games played. \n"
	   		+ "You can begin the game at any time by simply clicking a button, and your image will \n"
	   		+ "will display on the button. The game will go on until a win is made, or until all buttons \n"
	   		+ "have been pressed. On the bottom right of the screen, you can hit rematch to reset the game \n"
	   		+ "or navigate back to the main menu.\n");
	   	System.out.println("When you choose to customize your name the display window will be drooped \n"
	   			+ "and a series of prompts will lead you through the process in the console window. \n"
	   			+ "After you input your names you can choose to start the game, jump to the menu, \n"
	   			+ "go through the customization again, choose custom markers, or exit the game.\n");
	   	System.out.println("Inside the credits you can find some info on the creators of the game \n"
	   			+ "and when you're finished you can navigate back to the main menu \n");
	   System.out.println("Hit the 'Exit' button to close the window of this game.");
   } 
}
