package model;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import model.resources.Sprite;

public class Asteroid {
	Sprite sprite;
	boolean dead;
	
	//the position
	private double x;
	private double y;
	
	//the speed
	private double dx;
	private double dy;
	
	private double scaledWidth = 64;
	private double scaledHeight = 64;
	
	public Asteroid(int x, int y){
		
		this.x = x;
		this.y = y;
		
		try{
			BufferedImage spriteSheet = ImageIO.read(new File("asteroid64.png"));
			sprite = new Sprite(spriteSheet, 64, 64, 6, 5);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		//set the asteroid floating in a random direction at a random speed
		dx = (Math.random()*2)*Math.cos(Math.random()*(Math.PI*2));
		dy = (Math.random()*2)*Math.sin(Math.random()*(Math.PI*2));
	}
	
	public BufferedImage showSprite(){
		return sprite.displayFrame();
	}
	
	public void rotate(){
		sprite.nextFrame();
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
	
	public double getScaledWidth(){
		return scaledWidth;
	}
	
	public double getScaledHeight(){
		return scaledHeight;
	}
	
	public boolean isDead(){
		return dead;
	}
	
	public void setDead(boolean dead){
		this.dead = dead;
	}
}

