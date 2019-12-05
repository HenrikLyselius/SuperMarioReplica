package screenObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Mushroom extends ScreenObject2 implements ScreenObject{
	
	
	public Mushroom(int x, int y)
	{
		super(x, y);
		setDirectionLeft(false);
		setSpeed2(5);
		
		imageToBeShown = new ImageView(new Image("/media/Svamp.png"));
		changeImage();
	}
	


	public void movement(int freeSpace) 
	{
		if(this.getSpecialMovementTime() == 0) {
			if(Math.abs(freeSpace) <= Math.abs(getSpeed())){
				setXValue(freeSpace);
			}
			else if(directionLeft()) {
				setXValue(-getSpeed());
				}
			else {
				setXValue(getSpeed());
			}
			
			if(freeSpace == 0) {
				changeDirection();
			}
		}	
	}
	
	
	
	@Override
	public void specialMovement() 
	{
		if(getSpecialMovement()) {
				
			if(getSpecialMovementTime() > 0) {
					setYValue(-7);
			}
				
			setSpecialMovementTime(-1);
				
			if(getSpecialMovementTime() == 0) {
					setSpecialMovement(false);
			}
		}
	}
	
	
	public void changeImage() 
	{
		 imageToBeShown.setFitHeight(50);
		 imageToBeShown.setFitWidth(50);
		 imageToBeShown.setPreserveRatio(false);
		 imageToBeShown.setX(getXValue());
		 imageToBeShown.setY(getYValue());
	}
	

}
