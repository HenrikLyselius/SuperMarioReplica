package screenObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Hill extends ScreenObject2{

	public Hill(int x, int y) {
		super(x, y);
		imageToBeShown = new ImageView(new Image("/media/Kulle.png"));
		changeImage();
	}

	public void changeImage() {
		

		imageToBeShown.setFitHeight(66);
	 	imageToBeShown.setFitWidth(120);
	 	imageToBeShown.setPreserveRatio(false);
	 	imageToBeShown.setX(getXValue());
	 	imageToBeShown.setY(getYValue());
	}

}
