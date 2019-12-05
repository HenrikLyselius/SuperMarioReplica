package screenObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bush extends ScreenObject2{

	public Bush(int x, int y) {
		super(x, y);
		imageToBeShown = new ImageView(new Image("/media/Buske.png"));
		changeImage();
	}

	
	@Override
	public void changeImage() {
		
		imageToBeShown.setFitHeight(50);
	 	imageToBeShown.setFitWidth(100);
	 	imageToBeShown.setPreserveRatio(false);
	 	imageToBeShown.setX(getXValue());
	 	imageToBeShown.setY(getYValue());
	}
}
