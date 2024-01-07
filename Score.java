//Aarchi Singh
//June 9, 2023

//import necessary packages
import java.awt.*;

public class Score extends Rectangle{

	//define necessary variables
	static int GAME_WIDTH;
	static int GAME_HEIGHT;
	int player1;
	int player2;
	
	//constructor for score class
	Score(int GAME_WIDTH, int GAME_HEIGHT){
		Score.GAME_WIDTH = GAME_WIDTH; // Initialize the game width
		Score.GAME_HEIGHT = GAME_HEIGHT; // Initialize the game height
	}
	public void draw(Graphics g) {
		g.setColor(Color.white); //color of score text is white
		g.setFont(new Font("Consolas",Font.PLAIN,60)); //font adjustments
		
		//Draw a line in the middle of the game screen
		g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
		
		// Draw the player scores on the screen
		g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10), (GAME_WIDTH/2)-85, 50);
		g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10), (GAME_WIDTH/2)+20, 50);
		
		
		
	}
}