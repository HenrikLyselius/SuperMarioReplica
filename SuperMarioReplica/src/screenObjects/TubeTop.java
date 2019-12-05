package screenObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TubeTop extends StationaryObject{
	
	
	public TubeTop(int x, int y) {
		super(x, y);
		imageToBeShown = new ImageView(new Image("/media/TubeTop.png"));
		changeImage();
	}
	
	
	public void changeImage() {
		
		imageToBeShown.setX(getXValue()); 
		imageToBeShown.setY(getYValue()); 
		imageToBeShown.setFitWidth(100); 
		imageToBeShown.setFitHeight(50);
		imageToBeShown.setPreserveRatio(false);
	}

}
