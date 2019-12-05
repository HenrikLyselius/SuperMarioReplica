package screenObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class KoopaShell extends Monster{

	
	public KoopaShell(int x, int y) {
		
		super(x, y);
		super.setSpeed2(0);
		imageToBeShown = new ImageView(new Image("/media/KoopaSkalTransparentA.png"));
		setMonsterType(2);
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
	public void monsterMovement(int freeSpace) {
		
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
		
		if(getSpeed() == 21) {
			setSpeed2(11);
		}
	}
}
