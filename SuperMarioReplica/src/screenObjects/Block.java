package screenObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Block extends StationaryObject{

	private boolean canBeRemoved = true;
	private boolean goldCoin;
	private boolean mushroom;
	private boolean star;
	ImageView firmBlock;

	
	public Block(int x, int y) {
		super(x, y);
		firmBlock = new ImageView(new Image("/media/KlossTight.png"));
	}


	
	@Override
	public void specialMovement() {
		
		if(getSpecialMovementTime() > 7) {
			setYValue(-2);
		}
		
		if(getSpecialMovementTime() <= 7 && getSpecialMovementTime() > 0) {
			setYValue(2);
		}
		
		if(getSpecialMovementTime() > 0) {
			setSpecialMovementTime(-1);
		}
		else {
			setSpecialMovement(false);
		}
	}
	
	
	public boolean canBeRemoved() {
		return this.canBeRemoved;
	}
	
	public void setCanBeRemoved(boolean b) {
		this.canBeRemoved = b;
	}
	
	public boolean goldCoin() {
		return goldCoin;
	}
	
	public void setGoldCoin(boolean b) {
		this.goldCoin = b;
	}
	
	public boolean star() {
		return star;
	}
	
	public void setStar(boolean b) {
		this.star = b;
	}
	
	public boolean mushroom() {
		return mushroom;
	}
	
	public void setMushroom(boolean b) {
		this.mushroom = b;
	}
	
}
