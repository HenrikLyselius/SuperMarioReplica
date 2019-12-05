package screenObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlayingInstructions extends ScreenObject2{

	
	
	public PlayingInstructions(int x, int y) {
		super(x, y);
		
		imageToBeShown = new ImageView(new Image("/media/Spelinstruktioner4.png"));
		changeImage();
	}

	public void changeImage() {
		
		imageToBeShown.setFitHeight(250);
		imageToBeShown.setFitWidth(250);
		imageToBeShown.setPreserveRatio(false);
		imageToBeShown.setX(getXValue());
		imageToBeShown.setY(getYValue());
	}


}

