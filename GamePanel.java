//Aarchi Singh
//June 9, 2023

//import necessary packages
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

//declares a GamePanel class that is attached to the JPanel and implements the Runnable interface - an interface that contains a single method
public class GamePanel extends JPanel implements Runnable{

	//declares static (final) variables to define the game dimensions, ball diameter, and paddle dimensions
	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
	static final int BALL_DIAMETER = 20;
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;
	
	//declares an integer arraylist to store the score of each player after each round
	//these will be printed in the console as each player gains a point
	ArrayList<Integer> scoreHistory1 = new ArrayList<Integer>(); //for player 1
	ArrayList<Integer> scoreHistory2 = new ArrayList<Integer>(); //for player 2

	
	//Declaring various instances variables for the game
	//Instance variables are declared in a class, but outside a method, constructor or any block
	Thread gameThread; //this tread will allow a program to operate more efficiently by doing multiple things at the same time
	Image image;
	Graphics graphics;
	Random random;
	Paddle paddle1;
	Paddle paddle2;
	Ball ball;
	Score score;
	
	//constructor for GamePanel class
	GamePanel(){
		newPaddles(); //creates new paddles each round - calls method newPaddles
		newBall(); //creates new ping pong ball each round - calls method newBall
		score = new Score(GAME_WIDTH,GAME_HEIGHT); //score object
		this.setFocusable(true); ////sets focusable... allows panel to gain focus
		this.addKeyListener(new AL()); //key listener to determine what happens when user presses certain keys
		this.setPreferredSize(SCREEN_SIZE); //sets preferred size
		
		//creates new thread to start game
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void newBall() { //method that creates newBall each round
		random = new Random(); //allow for random position of ball - allows for unpredictibility in game
		
		//this sets the size of the ball
		ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);
	}
	public void newPaddles() { //method that creates new paddle objects
		//these parameters indicate where each paddle is positioned and its size
		paddle1 = new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
		//paddle 2 is positioned relative to paddle 1
		paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
	}
	public void paint(Graphics g) { //overriding paint method to draw game elements on panel 
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);
	}
	public void draw(Graphics g) { //this method will draw (display) the paddles, ball, and score
		paddle1.draw(g); //graphics class draws paddle 1
		paddle2.draw(g); //graphics class draws paddle 2
		ball.draw(g); //graphics class draws ball
		score.draw(g); //graphics class draws scores
		Toolkit.getDefaultToolkit().sync(); //this helps with the animation of the game

	}
	public void move() { //this method will move the paddles and ball
		paddle1.move();
		paddle2.move();
		ball.move();
	}
	public void checkCollision() { //method to check for collisions between the ball and window edges, paddles
		//it also updates the score and creates new paddles and ball when a player scores a point
		
		//bounce ball off top & bottom window edges
		//this can be thought of like a coordinate system
		if(ball.y <= 0) {
			ball.setYDirection(-ball.yVelocity);
		}
		if(ball.y >= GAME_HEIGHT-BALL_DIAMETER) {
			ball.setYDirection(-ball.yVelocity);
		}
		
		//bounce ball off paddles
		if(ball.intersects(paddle1)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity++; //optional for more difficulty... increases speed with each hit
			if(ball.yVelocity>0)
				ball.yVelocity++; //optional for more difficulty... increases speed with each hit
			else
				ball.yVelocity--; //speed will decrease if ball is not hit i.e. go back to original speed
			//distinctions between the x and y components of the ball's velocity
			ball.setXDirection(ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
		if(ball.intersects(paddle2)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity++; //optional for more difficulty... increases speed with each hit
			if(ball.yVelocity > 0)
				ball.yVelocity++; //optional for more difficulty... increases speed with each hit
			else
				ball.yVelocity--;
			ball.setXDirection(-ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
		
		//stops paddles at window edges
		//if statements essentially says that the paddle will be unable to go past the window
		//in the y direction (vertical)... this is the only direction they move in
		//hence, no x direction is coded
		if(paddle1.y<=0)
			paddle1.y=0;
		
		if(paddle1.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
			paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;
		
		if(paddle2.y<=0)
			paddle2.y=0;
		
		if(paddle2.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
			paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;
		
		//give a player 1 point with a hit and creates new paddles & ball
		if(ball.x <=0) { //if player 2 hits ball
			score.player2++; //adds 1 to player 2's score
			scoreHistory2.add(score.player2); //adds score to arraylist
			//resets game
			newPaddles();
			newBall();
			
			//for loop to iterate through arraylist to access play-by-play
			//updated scores on the console
			for (int i = 0; i < scoreHistory2.size(); i++) {
	            int score2 = scoreHistory2.get(scoreHistory2.size()-1);
	            System.out.println("Player 2: "+ score2);
	            
	          break;  
	        }
			
			//System.out.println("Player 2: "+score.player2);
		}
		if(ball.x >= GAME_WIDTH-BALL_DIAMETER) { //if player 1 hits ball
			score.player1++; //adds 1 to player 1's score
			scoreHistory1.add(score.player1); //adds score to arraylist
			//resets game
			newPaddles();
			newBall();
			
			//for loop to iterate through arraylist to access play-by-play
			//updated scores on the console
			for (int i = 0; i < scoreHistory1.size(); i++) {
	            int score1 = scoreHistory1.get(scoreHistory1.size()-1);
	            System.out.println("Player 1: "+ score1);
	            
	            break;
	            
	        }
			
			//System.out.println("Player 1: "+score.player1);
		}
	}
	public void run() {
		//game loop that runs the whole game
		//essentially calulates amount of time for ball to reach paddles
		//then, game resets at a fixed time rate
		long lastTime = System.nanoTime();
		double amountOfTicks =60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now -lastTime)/ns;
			lastTime = now;
			if(delta >=1) {
				move();
				checkCollision();
				repaint();
				delta--;
			}
		}
	}
	
	//class AL extends KeyAdapter to keep track of keyboard events for the paddles
	public class AL extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	}
}