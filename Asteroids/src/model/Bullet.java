package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import model.resources.Sprite;

public class Bullet {
	
	public static final int WIDTH = 5;
	public static final int HEIGHT = 5;
	
	//the position
	private double x;
	private double y;
	
	//the speed
	private double dx;
	private double dy;
	
	Sprite sprite;
	
	//take in a starting position and an angle to shoot at
	public Bullet(double x, double y, double dx, double dy){
		//create the "Sprite Sheet"
		BufferedImage spriteSheet = new BufferedImage(5, 5, BufferedImage.TYPE_INT_RGB);
		Graphics g = spriteSheet.getGraphics();
		g.setColor(Color.green);
		g.fillRect(0, 0, 5, 5);
		
		//create the sprite
		sprite = new Sprite(spriteSheet, 5, 5, 1, 1);
		
		//set the initial position
		this.x = x;
		this.y = y;
		
		//the bullet should go much faster than the ship
		this.dx = 20*dx;
		this.dy = 20*dy;
	}
	
	public BufferedImage showSprite(){
		return sprite.displayFrame();
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
}
