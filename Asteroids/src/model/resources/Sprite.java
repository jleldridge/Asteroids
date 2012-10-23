package model.resources;

import java.awt.image.BufferedImage;

public class Sprite {
	private BufferedImage spriteSheet;
	private BufferedImage[] frames;
	private int frameWidth;
	private int frameHeight;
	private int currentFrame;
	private int numFrames;
	
	public Sprite(BufferedImage spriteSheet, int frameWidth, int frameHeight, int numRows, int numColumns){
		this.spriteSheet = spriteSheet;
		frames = new BufferedImage[numRows*numColumns];
		currentFrame = 0;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		numFrames = numRows * numColumns;
		
		for(int i = 0, k = 0; i < numRows; i++){
			for(int j = 0; j < numColumns; j++, k++){
				frames[k] = spriteSheet.getSubimage(j*frameWidth, i*frameHeight, frameWidth, frameHeight);
			}
		}
	}

	public int getNumFrames(){
		return numFrames;
	}
	
	public int getCurrentFrame() {
		return currentFrame;
	}

	public void setCurrentFrame(int currentFrame) {
		this.currentFrame = currentFrame;
	}
	
	public BufferedImage displayFrame(){
		return frames[currentFrame];
	}
	
	public void nextFrame(){
		currentFrame++;
		if(currentFrame >= numFrames){
			currentFrame = 0;
		}
	}
	
	public void previousFrame(){
		currentFrame--;
		if(currentFrame < 0){
			currentFrame = numFrames-1;
		}
	}
	
	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}
	
	public void setSpriteSheet(BufferedImage spriteSheet) {
		this.spriteSheet = spriteSheet;
	}
	
	public int getFrameWidth() {
		return frameWidth;
	}

	public int getFrameHeight() {
		return frameHeight;
	}
}
