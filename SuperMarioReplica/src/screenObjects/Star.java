package screenObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Star extends ScreenObject2{
	
	private int jumpTime = 0;
	private boolean jumps; 
	
	
	public Star(int x, int y) {
		super(x + 10, y - 50);
		setSpeed2(5);
		setDirectionLeft(false);
		
		imageToBeShown = new ImageView(new Image("/media/Star.png"));
		changeImage();
	}


	
	public int getJumpTime() {
		return jumpTime;
	}
	
	public void setJumpTime(int change) {
		this.jumpTime = change;
	}

	public void changeJumpTime(int change) {
		this.jumpTime = this.jumpTime + change;
	}

	public boolean jumps() {
		return jumps;
	}

	
	public void setJumps(boolean b) {
		this.jumps = b;
	}

	@Override
	public void fall(int change) {
		if(this.getYValue() < 600){
			setYValue(Math.min(getFallSpeed(), change));
		}
		
		if(change == 0) {
			setJumps(true);
			setJumpTime(5);
		}
	}
	
	public void jump(int freeSpace) {
		setYValue(Math.max(freeSpace, -17));
		changeJumpTime(-1);
		
		if(freeSpace == 0 || getJumpTime() == 0) {
			setJumps(false);
			setJumpTime(0);
		}
	}

	public void changeImage() {
		
		 imageToBeShown.setFitHeight(50);
		 imageToBeShown.setFitWidth(50);
		 imageToBeShown.setPreserveRatio(false);
		 imageToBeShown.setX(getXValue());
		 imageToBeShown.setY(getYValue());
	}
	
	
	public void movement(int freeSpace) 
	{
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
	}

	

}
