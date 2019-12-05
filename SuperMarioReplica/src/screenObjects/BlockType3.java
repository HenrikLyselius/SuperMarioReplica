package screenObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BlockType3 extends Block{
	
	
	public BlockType3(int x, int y) {
		
		super(x, y);
		imageToBeShown = new ImageView(new Image("/media/BlockTyp3.png"));
		changeImage();
	}
	
	
	@Override
	public void changeImage() {
		
		if(canBeRemoved() == false) {
			imageToBeShown = firmBlock;
		}
		imageToBeShown.setX(getXValue()); 
		imageToBeShown.setY(getYValue()); 
		imageToBeShown.setFitWidth(50); 
		imageToBeShown.setFitHeight(50);
		imageToBeShown.setPreserveRatio(false);
	}

}
