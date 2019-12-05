package screenObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class QuestionMarkBlock extends Block{

	private ImageView bright;
	private ImageView mediumBright;
	private ImageView mediumDark;
	private ImageView dark;
	private int imageHelp = 0;
	private int imageHelp2 = 0;
	
	
	public QuestionMarkBlock(int x, int y) {
		super(x, y);
		
		bright = new ImageView(new Image("/media/QuestionBlockBrightest.png"));
		mediumBright = new ImageView(new Image("/media/QuestionBlockBright.png"));
		mediumDark = new ImageView(new Image("/media/QuestionBlockMedium.png"));
		dark = new ImageView(new Image("/media/QuestionBlockDark.png"));
		imageToBeShown = bright;
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
		
	
	
	// Changes which image is to be shown, so that you get a harmonic result. 
	
	public void chooseImage() { 	
		
		imageHelp2 = imageHelp2 + 1;
		
		if(imageHelp2 == 5) {
			
			imageHelp = imageHelp + 1;
		
			if(imageHelp % 4 == 0) {
				imageToBeShown = dark;
			}
			
			if(imageHelp % 4 == 1) {
				imageToBeShown = mediumDark;
			}
			
			if(imageHelp % 4 == 2) {
				imageToBeShown = mediumBright;
			}
			
			if(imageHelp % 4 == 3) {
				imageToBeShown = bright;
			}
			
			imageHelp2 = 0;
		}
		
		if(imageHelp == 40000) {
			imageHelp = 0;
		}
		
		
		changeImage();
		
	}
}
