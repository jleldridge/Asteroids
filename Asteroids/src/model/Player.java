package model;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import model.resources.Sprite;

public class Player {
	public static final int WIDTH = 64, HEIGHT = 64;
	//stores the number of frames of rotation for each quadrant of the x, y plane
	double framesPerQuadrant;
	
	//stores the "slope" for each frame in the sprite sheet where row 0 is the x component and row 1 is the y component
	private double[][] m;
	
	//the player's sprite
	Sprite sprite;
	private BufferedImage deathSprites[];
	
	//the player's position
	private double x;
	private double y;
	
	//the player's speed in whatever direction
	private double dx;
	private double dy;
	
	//constructs a new player with the specified position
	public Player(int x, int y){
		this.x = x;
		this.y = y;
		try{
			BufferedImage spriteSheet = ImageIO.read(new File("C:/Users/Mosrael/Workshop/Asteroids/playerShip.png"));
			sprite = new Sprite(spriteSheet, WIDTH, HEIGHT, 12, 6);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		deathSprites = new BufferedImage[3];
		for(int i = 0; i < 3; i++){
			try{
			deathSprites[i] = ImageIO.read(new File("C:/Users/Mosrael/Workshop/Asteroids/ShipDeathframe" + (i+1) + ".png"));
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		
		//now that numFrames is known, initialize framesPerQuadrant and m based on that
		framesPerQuadrant = sprite.getNumFrames()/4 ;
		m = new double[sprite.getNumFrames()][2];
		
		//use trigonometry properties to find the x and y parts of the slope for
		//each frame of rotation
		for(int i = 0; i < sprite.getNumFrames(); i++){
			//because 0 points right on the unit circle an adjustment of -PI/2 is made
			m[i][0] = Math.cos(((2*(Math.PI)/72)*i) - Math.PI/2);
			m[i][1] = Math.sin(((2*(Math.PI)/72)*i) - Math.PI/2);
		}
	}
	
	public BufferedImage showSprite(){
		return sprite.displayFrame();
	}
	public BufferedImage showDeathSprite(int i){
		return deathSprites[i];
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}
	
	public void rotateCW(){
		sprite.nextFrame();
		//System.out.println(m[sprite.getCurrentFrame()]);
	}
	
	public void rotateCCW(){
		sprite.previousFrame();
	}
	
	//I have no clue if this will work yet
	public void boost(){
		dx += (0.1)*m[sprite.getCurrentFrame()][0];
		dy += (0.1)*m[sprite.getCurrentFrame()][1];
	}
	
	public Bullet shoot(){
		Bullet b = new Bullet(x, y, m[sprite.getCurrentFrame()][0], m[sprite.getCurrentFrame()][1]);
		return b;
	}
}