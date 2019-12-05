package screenObjects;

import graphics.ImageModelling;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MonsterKoopa extends Monster{
	
	
	private ImageView koopaLeft;
	private ImageView koopaLeft2;
	private ImageView koopaRight;
	private ImageView koopaRight2;
	private int imageHelp = 0;
	
	
	public MonsterKoopa(int x, int y) {
		
		super(x, y);
		super.setSpeed2(3);
		this.koopaLeft = new ImageView(new Image("/media/KoopaLeftA.png"));
		this.koopaRight = ImageModelling.flipImageHorizontally(koopaLeft);
		this.koopaLeft2 = new ImageView(new Image("/media/KoopaPosition2A.png"));
		this.koopaRight2 = ImageModelling.flipImageHorizontally(koopaLeft2);
		imageToBeShown = koopaLeft;
		changeImage();
		
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
	public void chooseImage() {
		
		if(imageHelp % 3 == 0) {
			exchangeImage();
		}
		
		
		if(imageHelp < 40000) {
			imageHelp = imageHelp + 1;
		}
		else {
			imageHelp = 0;
		}
	}
	
	
	public void exchangeImage() {
		if(directionLeft()) {
			if(imageToBeShown == koopaLeft) {
				imageToBeShown = koopaLeft2;
			}
			else {
				imageToBeShown = koopaLeft;
			}
		}
		
		else 
			if(imageToBeShown == koopaRight) {
			imageToBeShown = koopaRight2;
		}
			else {
				imageToBeShown = koopaRight;
		}
	}

}
