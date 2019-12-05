package screenObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FireBall extends ScreenObject2 implements ScreenObject{

	
	public FireBall(int x, int y, boolean b, boolean playerBig){
		super(0, 0);
		imageToBeShown = new ImageView(new Image("/media/Eldboll.png"));
		
		if(playerBig) {
			super.setYValue(y + 40);
		}
		else {
			super.setYValue(y + 10);
		}
		
		if(directionLeft()) {
			super.setXValue(x - 20);
		}
		else {
			super.setXValue(x + 50);
		}
		
		setDirectionLeft(b);
		setSpeed2(20);

		changeImage();
	}
	
	
	
	@Override
	public void changeImage() {
		 imageToBeShown.setFitHeight(20);
		 imageToBeShown.setFitWidth(20);
		 imageToBeShown.setPreserveRatio(false);
		 imageToBeShown.setX(getXValue());
		 imageToBeShown.setY(getYValue());
	}
}
