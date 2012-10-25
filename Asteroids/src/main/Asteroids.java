package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.KeyController;

import engine.Engine;

public class Asteroids extends JFrame{
	public Engine engine;
	public Canvas canvas;
	KeyController controller;
	boolean isRunning = true;

	public Asteroids() {
		super("Asteroids");
		//setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setOpacity(0.80f);
		
		//I don't know why exactly we do this
		setIgnoreRepaint(true);
		
		// create the area to draw on
		canvas = new Canvas();
		// set its size
		canvas.setSize(800, 600);
		
		//I don't why exactly we do this
		canvas.setIgnoreRepaint(true);
		
		// add it to the jframe
		add(canvas);
		// arrange it in the jframe
		pack();
		// make the jframe visible
		setVisible(true);
		
		controller = new KeyController();
		canvas.addKeyListener(controller);
		engine = new Engine(controller);
	}
	
	public void restartGame(){
		engine = new Engine(controller);
	}
}
