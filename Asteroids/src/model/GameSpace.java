package model;

import java.util.ArrayList;

public class GameSpace {
	//the dimensions of the game space
	final public static int WIDTH = 800;
	final public static int HEIGHT = 600;
	
	//the entities within the game space
	private Player player;
	private ArrayList<Asteroid> asteroids;
	private ArrayList<Bullet> bullets;
	
	public GameSpace(){
		//place the player in the game space
		player = new Player(WIDTH/2, HEIGHT/2);
		
		//initialize storage space for asteroids and bullets
		asteroids = new ArrayList<Asteroid>();
		bullets = new ArrayList<Bullet>();
		
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Asteroid> getAsteroids() {
		return asteroids;
	}
	
	public void addAsteroid(Asteroid a){
		asteroids.add(a);
	}

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}
	
	public void addBullet(Bullet b){
		bullets.add(b);
	}
}
