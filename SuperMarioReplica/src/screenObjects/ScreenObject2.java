package screenObjects;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Shape;

public class ScreenObject2 implements ScreenObject{
	
	private int xValue;
	private int yValue;
	private int speed;
	private int fallSpeed = 7;
	private boolean directionLeft;
	private boolean specialMovement = false;
	private int specialMovementTime = 0;
	private int animationTime = 0;
	ImageView imageToBeShown;


	public ScreenObject2(int x, int y) {
		xValue = x;
		yValue = y;
	}
	
	
	public int getXValue() {
		return this.xValue;
	}
	
	public int getYValue() {
		return this.yValue;
	}
	
	public void setXValue(int change) {
		this.xValue = this.xValue + change;
		changeImage();
	}
	
	public void setYValue(int change) {
		this.yValue = this.yValue + change;
		changeImage();
	}
	
	public int getFallSpeed() {
		return this.fallSpeed;
	}
	
	public void setFallSpeed(int change) {
		this.fallSpeed = change;
	}
	
	public int getSpecialMovementTime() {
		return specialMovementTime;
	}
	
	public void setSpecialMovementTime(int change) {
		this.specialMovementTime = this.specialMovementTime + change;
	}
	
	public void changeImage() {
		
	}
	
	public boolean getSpecialMovement() {
		return specialMovement;
	}
	
	public void setSpecialMovement(boolean b) {
		this.specialMovement = b;
	}
	
	public void specialMovement() {
		
	}
	
	public int getAnimationTime() {
		return this.animationTime;
	}
	
	public void setAnimationTime(int change) {
		this.animationTime = this.animationTime + change;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public void setSpeed() {
	}
	
	public void setSpeed2(int speed) {
		this.speed = speed;
	}
	
	public boolean directionLeft() {
		return directionLeft;
	}
	
	public void setDirectionLeft(boolean b) {
		directionLeft = b;
	}
	
	public void changeDirection() {
		if(directionLeft()) {
			setDirectionLeft(false);
		}
		else {
			setDirectionLeft(true);
		}
	}
	
	public void fall(int change) {
	}
	
	public ImageView getImageView() {
		return imageToBeShown;
	}
	
	public void animation() {
	}

	public int getHeight() {
		return (int) imageToBeShown.getFitHeight();
	}
	
	public int getWidth() {
		return (int) imageToBeShown.getFitWidth();
	}
	
	public Shape getObject() {
		return null;
	}

}
