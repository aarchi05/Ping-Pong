//Aarchi Singh
//June 9, 2023

//import necessary packages
import java.awt.*;
import javax.swing.*;

//this class is linked to the JFrame
public class GameFrame extends JFrame{

	//declaring GamePanel object
	GamePanel panel;
	
	GameFrame(){ //constructor for GameFrame class
		panel = new GamePanel(); //creating a new instance of the GamePanel class and assigning it to the panel object
		this.add(panel); //adds panel to JFrame
		this.setTitle("Pong Game"); //sets Title of frame to Pong Game
		this.setResizable(false); //this ensures window cannot be resized by user
		this.setBackground(Color.black); //frame background is black
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //ensures application fully stops once user exits
		this.pack(); //automatically resizes to fit components
		this.setVisible(true); //allows frame to be visible to user
		this.setLocationRelativeTo(null); //this ensures the window pops up right in the center
	}
}
