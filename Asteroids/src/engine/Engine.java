package engine;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

import model.Asteroid;
import model.Bullet;
import model.GameSpace;
import model.Player;

public class Engine {
	GameSpace space;
	boolean rotateCW = false;
	boolean rotateCCW = false;
	boolean boost = false;
	boolean shoot = false;
	public boolean dead = false;
	public int shipDeathCounter = 0;
	public long score = 0;
	int framesBetweenAsteroids = 120;
	int framesBetweenAsteroidRotations = 5;
	BufferedImage gameOver;

	public Engine() {
		try {
			gameOver = ImageIO.read(new File("game_over.jpg"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// create the game space (which will initialize the player)
		space = new GameSpace();
	}

	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_RIGHT) {
			rotateCW = true;
			rotateCCW = false;
		} else if (code == KeyEvent.VK_LEFT) {
			rotateCCW = true;
			rotateCW = false;
		}

		if (code == KeyEvent.VK_UP) {
			boost = true;
		}

		if (code == KeyEvent.VK_SPACE) {
			shoot = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_RIGHT) {
			rotateCW = false;
		} else if (code == KeyEvent.VK_LEFT) {
			rotateCCW = false;
		}

		if (code == KeyEvent.VK_UP) {
			boost = false;
		}
	}

	public void update() {
		Player player = space.getPlayer();
		ArrayList<Asteroid> asteroids = space.getAsteroids();
		ArrayList<Bullet> bullets = space.getBullets();

		// add a new asteroid if any are missing
		if (asteroids.size() < 10 && framesBetweenAsteroids <= 0) {
			space.addAsteroid(new Asteroid(10, 10));
			framesBetweenAsteroids = 120;
		} else {
			framesBetweenAsteroids--;
		}

		if (!dead) {
			// check what controls have been pressed
			if (boost) {
				player.boost();
			}
			if (shoot) {
				space.addBullet(player.shoot());
				shoot = false;
			}
			if (rotateCW) {
				player.rotateCW();
			} else if (rotateCCW) {
				player.rotateCCW();
			}

			// check for collisions
			// player with right wall
			if (player.getX() >= GameSpace.WIDTH) {
				player.setX(-50);
			}
			// player with left wall
			if (player.getX() <= -60) {
				// System.out.println(player.getX());
				player.setX(GameSpace.WIDTH - 10);
			}
			// player with top wall
			if (player.getY() <= -60) {
				player.setY(GameSpace.HEIGHT - 10);
			}
			// player with bottom wall
			if (player.getY() >= GameSpace.HEIGHT) {
				player.setY(-50);
			}
		}
		// asteroid collisions
		Iterator<Asteroid> iter = asteroids.iterator();
		while (iter.hasNext()) {

			Asteroid a = iter.next();
			// asteroid with right wall
			if (a.getX() >= GameSpace.WIDTH) {
				a.setX(-50);
			}
			// asteroid with left wall
			if (a.getX() <= -60) {
				a.setX(GameSpace.WIDTH - 10);
			}
			// asteroid with top wall
			if (a.getY() <= -60) {
				a.setY(GameSpace.HEIGHT - 10);
			}
			// asteroid with bottom wall
			if (a.getY() >= GameSpace.HEIGHT) {
				a.setY(-50);
			}

			// asteroid with player
			// SEEMS TO WORK
			if (a.getX() + a.getScaledWidth() >= player.getX()
					&& a.getX() <= player.getX() + Player.WIDTH) {
				if (a.getY() <= player.getY() + Player.HEIGHT
						&& a.getY() + a.getScaledHeight() >= player.getY()) {
					dead = true;
				}
			}

			// asteroid with bullets
			Iterator<Bullet> biter = bullets.iterator();

			while (biter.hasNext()) {
				Bullet b = biter.next();

				if (a.getX() + a.getScaledWidth() >= b.getX()
						&& a.getX() <= b.getX() + Bullet.WIDTH) {
					if (a.getY() <= b.getY() + Bullet.HEIGHT
							&& a.getY() + a.getScaledHeight() >= b.getY()) {
						iter.remove();
						biter.remove();
						score += 100;
					}
				}

				if (b.getY() > GameSpace.HEIGHT || b.getY() < 0
						|| b.getX() > GameSpace.WIDTH || b.getX() < 0) {
					biter.remove();
				}
			}
		}

		// update the state of the player
		player.setX(player.getX() + player.getDx());
		player.setY(player.getY() + player.getDy());

		// update the state of the asteroids
		for (Asteroid a : asteroids) {
			a.setX(a.getX() + a.getDx());
			a.setY(a.getY() + a.getDy());
		}
		// update the state of the bullets
		for (Bullet b : bullets) {
			b.setX(b.getX() + b.getDx());
			b.setY(b.getY() + b.getDy());
		}
	}

	public void render(Graphics g) {
		if (dead) {
			if(shipDeathCounter < 3){
				g.drawImage(space.getPlayer().showDeathSprite(shipDeathCounter), (int)space.getPlayer().getX(), (int)space.getPlayer().getY(), null);
				shipDeathCounter++;
			}
			else{
				g.drawImage(gameOver, GameSpace.WIDTH / 2 - 150,
						GameSpace.HEIGHT / 2 - 68, null);
				shipDeathCounter++;
			}
		} else {
			Player player = space.getPlayer();
			ArrayList<Asteroid> asteroids = space.getAsteroids();
			ArrayList<Bullet> bullets = space.getBullets();

			// render the player
			g.drawImage(player.showSprite(), (int) player.getX(),
					(int) player.getY(), null);
		}
		// render the asteroids
		for (Asteroid a : space.getAsteroids()) {
			g.drawImage(a.showSprite(), (int) a.getX(), (int) a.getY(), null);
			if(framesBetweenAsteroidRotations <= 0){
				a.rotate();
			}
		}
		if(framesBetweenAsteroidRotations <= 0){
			framesBetweenAsteroidRotations = 5;
		}
		else{
			framesBetweenAsteroidRotations--;
		}
		// render the bullets
		for (Bullet b : space.getBullets()) {
			g.drawImage(b.showSprite(), (int) b.getX(), (int) b.getY(), null);
		}
	}
}
