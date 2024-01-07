//Aarchi Singh
//June 9, 2023

//import necessary packages
import java.awt.*;
import java.util.*;

public class Ball extends Rectangle{

	//defining necessary variables
	Random random;
	int xVelocity;
	int yVelocity;
	int initialSpeed = 3; //balls initial speed at the start of each round
	
	//constructor for Ball class, defining dimensions
	Ball(int x, int y, int width, int height){
		super(x,y,width,height); //can use super keyword to access the data member or field of parent class
		
		random = new Random();
		
		//generates random directions for ball
		int randomXDirection = random.nextInt(3);
		if(randomXDirection == 0)
			randomXDirection--;
		
		setXDirection(randomXDirection*initialSpeed);
		
		int randomYDirection = random.nextInt(3);
		
		if(randomYDirection == 0)
			randomYDirection--;
		
		setYDirection(randomYDirection*initialSpeed);
		
	}
	
	//these two methods set random velocities for ball corresponding to random directions
	public void setXDirection(int randomXDirection) {
		xVelocity = randomXDirection;
		
	}
	
	public void setYDirection(int randomYDirection) {
		yVelocity = randomYDirection;
		
	}
	
	public void move() {
		x += xVelocity; // Update the x-coordinate of the ball based on its velocity
		y += yVelocity; // Update the y-coordinate of the ball based on its velocity
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.white); //sets color of ball
		g.fillOval(x, y, height, width); // Fill an oval representing the ball on the graphics context
	}
		
	}
