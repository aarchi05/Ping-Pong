//Aarchi Singh
//June 9, 2023

//import necessary packages
import java.awt.*;
import java.awt.event.*;

public class Paddle extends Rectangle{

	int id;
	int yVelocity;
	int speed = 10;
	
	//constructor for Paddle class, defines dimensions of paddle
	Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
		super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
		this.id=id;
	}
	
	public void keyPressed(KeyEvent e) {
		// Handle key presses based on the paddle ID
		switch(id) {
		case 1:
			if(e.getKeyCode()==KeyEvent.VK_W) { //W moves paddle upwards
				setYDirection(-speed);
			}
			if(e.getKeyCode()==KeyEvent.VK_S) { //S moves paddle downwards
				setYDirection(speed);
			}
			break;
		case 2:
			if(e.getKeyCode()==KeyEvent.VK_UP) {//up arrow key moves paddle upwards
				setYDirection(-speed);
			}
			if(e.getKeyCode()==KeyEvent.VK_DOWN) { //down arrow key moves paddle upwards
				setYDirection(speed);
			}
			break;
		}
	}
	public void keyReleased(KeyEvent e) {
		// Handle key releases based on the paddle ID
		switch(id) {
		case 1:
			if(e.getKeyCode()==KeyEvent.VK_W) {
				setYDirection(0); //Stops paddle movement
			}
			if(e.getKeyCode()==KeyEvent.VK_S) {
				setYDirection(0); //Stops paddle movement
			}
			break;
		case 2:
			if(e.getKeyCode()==KeyEvent.VK_UP) {
				setYDirection(0); //Stops paddle movement
			}
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				setYDirection(0); //Stops paddle movement
			}
			break;
		}
	}
	public void setYDirection(int yDirection) {
		yVelocity = yDirection; //sets the paddle's y direction to its corresponding velocity
	}
	public void move() {
		y= y + yVelocity; //Updates the y-coordinate of the paddle based on its velocity
	}
	public void draw(Graphics g) { //draws the paddles and gives them corresponding colors
		if(id==1)
			g.setColor(Color.blue);
		else
			g.setColor(Color.red);
		g.fillRect(x, y, width, height); //Fills the rectangle representing the paddle on the graphics context
	}
}