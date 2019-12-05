package screenObjects;

import javafx.scene.image.ImageView;

public class Monster extends ScreenObject2{
	
	private int monsterType = 1;
	private double rotation;
	
	private boolean followPlayer = true;
	
	
	public Monster(int x, int y) {
		super(x, y);
		setSpeed2(2);
	}
	

	
	public int getMonsterType() {
		return monsterType;
	}
	
	public void setMonsterType(int i) {
		monsterType = i;
	}
	
	
	
	public void fall(int change) {
		if(this.getYValue() < 600) {
			setYValue(Math.min(change, getFallSpeed()));
		}
	}
	

	public void changeImage() {
		
		 imageToBeShown.setFitHeight(50);
		 imageToBeShown.setFitWidth(50);
		 imageToBeShown.setPreserveRatio(false);
		 imageToBeShown.setX(getXValue());
		 imageToBeShown.setY(getYValue());
	}
	
	
	@Override
	public ImageView getImageView() {
		changeImage();
		return imageToBeShown;
	}
	
	public void animation() {
		if(getAnimationTime() > 12) {
			setYValue(-14);
			setAnimationTime(-1);
		}
		if(getAnimationTime() <= 12 && getAnimationTime() > 0) {
			 setYValue(5);
			 setAnimationTime(-1);
			 this.rotation = this.rotation + 10;
			 imageToBeShown.setRotate(rotation);
		}
	}

	
	
	public void monsterMovement(int friYta) {
		if(Math.abs(friYta) <= getSpeed()) {
			setXValue(friYta);
		}
		else if(directionLeft()) {
			setXValue(-getSpeed());
			}
		else {
			setXValue(getSpeed());
		}
	}
	
	
	public boolean getfollowPlayer() {
		return followPlayer;
	}

	public void chooseImage() {
		
	}

}
