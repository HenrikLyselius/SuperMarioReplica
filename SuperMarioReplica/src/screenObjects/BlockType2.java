package screenObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BlockType2 extends Block{
	
	
	public BlockType2(int x, int y) {
		super(x, y);
		imageToBeShown = new ImageView(new Image("/media/BlockMarkenLjust.png"));
		changeImage();
	}
	


	
	@Override
	public void changeImage() {
		
		imageToBeShown.setX(getXValue()); 
		imageToBeShown.setY(getYValue()); 
		imageToBeShown.setFitWidth(50); 
		imageToBeShown.setFitHeight(50);
		imageToBeShown.setPreserveRatio(false);
	}
	



}
