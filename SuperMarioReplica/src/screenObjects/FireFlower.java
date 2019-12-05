package screenObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FireFlower extends ScreenObject2{

	
	public FireFlower(int x, int y) {
		super(x, y);
		imageToBeShown = new ImageView(new Image("/media/Eldblomma.png"));
		changeImage();
	}
	
	
	@Override
	public void changeImage() {
	
		imageToBeShown.setFitHeight(50);
	 	imageToBeShown.setFitWidth(50);
	 	imageToBeShown.setPreserveRatio(false);
	 	imageToBeShown.setX(getXValue());
	 	imageToBeShown.setY(getYValue());
	}


	
	@Override
	public void specialMovement() {
		if(getSpecialMovement()) {
				
			if(getSpecialMovementTime() > 0) {
					setYValue(-2);
			}
				
			setSpecialMovementTime(-1);
				
			if(getSpecialMovementTime() == 0) {
					setSpecialMovement(false);
			}
		}
	}
	

}
