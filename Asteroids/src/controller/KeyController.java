package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

public class KeyController implements KeyListener {
	private Stack<Integer> keysDown;
	private boolean boosting;
	private boolean shooting;
	
	public KeyController(){
		keysDown = new Stack<Integer>();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP){
			boosting = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_SPACE){
			shooting = true;
		}
		else if(!keysDown.contains(e.getKeyCode())){
			keysDown.push(e.getKeyCode());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP){
			boosting = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_SPACE){
			shooting = false;
		}
		else{
			keysDown.remove(Integer.valueOf(e.getKeyCode()));
		}
	}
	
	public Stack<Integer> getKeysDown(){
		return keysDown;
	}
	
	public boolean isBoosting(){
		return boosting;
	}
	
	public boolean isShooting(){
		return shooting;
	}
	public void setShooting(boolean shooting){
		this.shooting = shooting;
	}
	
	@Override
	public void keyTyped(KeyEvent arg0) {
		//not used
	}

}
