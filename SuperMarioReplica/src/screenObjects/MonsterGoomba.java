package screenObjects;

import graphics.ImageModelling;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MonsterGoomba extends Monster{

	private ImageView monsterA;
	private ImageView monsterB;
	private int imageHelp = 0;
	
	
	public MonsterGoomba(int x, int y) {
		super(x, y);
		this.monsterA = new ImageView(new Image("/media/MonsterNrEtt.png"));
		this.monsterB = ImageModelling.flipImageHorizontally(monsterA);
		imageToBeShown = monsterA;
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
	
	
	private void exchangeImage() {
		if(imageToBeShown == monsterA) {
			imageToBeShown = monsterB;
		}
		else {
			imageToBeShown = monsterA;
		}
	}
}
