package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import model.GameSpace;

import engine.Engine;

public class Driver {

	public static void main(String[] args) {
		final int FRAMES_PER_SECOND = 60;
		JFrame.setDefaultLookAndFeelDecorated(true);
		Asteroids game = new Asteroids();
		

		// this might be what's needed to make a jframe able to handle constant
		// repainting of the screen. After all, such a thing would be handled in
		// a web
		// browser already which why be why this is uncomplicated in applets.

		// note: enabling the first line seems to make the game loop run faster,
		// this isn't necessarily
		// good for playability in this game, but something that should be kept
		// in mind
		// game.canvas.createBufferStrategy(2);
		// BufferStrategy buffer = game.canvas.getBufferStrategy();

		// the image to draw to
		BufferedImage screen = new BufferedImage(800, 600,
				BufferedImage.TYPE_INT_RGB);
		// the image graphics
		Graphics g = screen.getGraphics();
		// the canvas graphics
		Graphics cg = game.canvas.getGraphics();

		while (true) {
			//to limit fps and game speed
			double start = System.currentTimeMillis();
			
			g.setColor(Color.black);
			g.fillRect(0, 0, 800, 600);
			g.setColor(Color.white);
			g.setFont(new Font("Arial Bold", Font.PLAIN, 24));
			g.drawString("Score: " + game.engine.score, 0, 20);

			// update the game state
			game.engine.update();
			// render the image reflecting the current game state
			game.engine.render(g);

			cg.drawImage(screen, 0, 0, null);

			//my attempt at limiting game loop speed by just syncing
			//update speed with fps. Works fairly well, though I can see
			//where it's a problem: the ship becomes less responsive
			//to commands when the frames per second is set to a lower value
			//this makes sense since the game would be literally checking for
			//if the key presses have taken place fewer times per second.
			double end = System.currentTimeMillis();
			if(game.engine.dead && game.engine.shipDeathCounter >= 4){
				try{
					Thread.sleep(5000);
				}catch(Exception e){
					System.out.println("well that didn't work :(");
				}
				game.engine = new Engine();
			}
			if((end-start) < (1000/FRAMES_PER_SECOND)){
				try{
					Thread.sleep((long)((1000/FRAMES_PER_SECOND)-(end-start)));
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
			}
		}
	}
}
