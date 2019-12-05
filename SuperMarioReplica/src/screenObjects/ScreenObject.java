package screenObjects;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Shape;

public interface ScreenObject {

	int getXValue();
	
	void setXValue(int change);
	
	int getYValue();
	
	void setYValue(int change);
	
	int getWidth();
	
	int getHeight();
	
	int getSpeed();
	
	void setSpeed();

	void changeImage();
	
	int getAnimationTime();
	
	void setAnimationTime(int change);
	
	void fall(int change);
	
	int getFallSpeed();
	
	boolean directionLeft();
	
	void setDirectionLeft(boolean b);
	
	Shape getObject();
	
	ImageView getImageView();
	
	void setSpecialMovement(boolean b);
	
	boolean getSpecialMovement();
	
	void specialMovement();
	
	void animation();
	
	
}
