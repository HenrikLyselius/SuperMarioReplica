package screenObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Cloud extends ScreenObject2{
	
	
	public Cloud(int x, int y) {
		super(x, y);
		imageToBeShown = new ImageView(new Image("/media/Moln.png"));
		changeImage();
	}

	
	@Override
	public void changeImage() {
		

		imageToBeShown.setFitHeight(75);
	 	imageToBeShown.setFitWidth(100);
	 	imageToBeShown.setPreserveRatio(false);
	 	imageToBeShown.setX(getXValue());
	 	imageToBeShown.setY(getYValue());
	}


	

}
