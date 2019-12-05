package screenObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FlagPole extends StationaryObject{
	
	
	public FlagPole(int x, int y) {
		super(x, y);
		imageToBeShown = new ImageView(new Image("/media/FlagPole.png"));
		changeImage();
	}

	
	
	@Override
	public void changeImage() {
		
		imageToBeShown.setX(getXValue()); 
		imageToBeShown.setY(getYValue()); 
		imageToBeShown.setFitWidth(110); 
		imageToBeShown.setFitHeight(400);
		imageToBeShown.setPreserveRatio(false);
	}

}
