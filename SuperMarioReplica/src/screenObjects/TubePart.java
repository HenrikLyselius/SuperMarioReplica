package screenObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TubePart extends StationaryObject {
	
	
	public TubePart(int x, int y) {
		super(x, y);
		imageToBeShown = new ImageView(new Image("/media/TubePart.png"));
		changeImage();
	}

	
	@Override
	public void changeImage() {
		
		imageToBeShown.setX(getXValue()); 
		imageToBeShown.setY(getYValue()); 
		imageToBeShown.setFitWidth(100); 
		imageToBeShown.setFitHeight(100);
		imageToBeShown.setPreserveRatio(false);
	}

}
