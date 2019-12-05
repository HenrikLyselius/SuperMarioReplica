package screenObjects;

import graphics.ImageModelling;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends ScreenObject2 implements ScreenObject{
	
	private int xAbsolutValue;
	private int secondLatestXAbsolutValue;
	private int secondLatestXInSpace;
	private int secondLatestXInTime;
	private int xWiseChangeSpeed[] = new int[4];
	private int xWiseChangeSpeedHelp = 0;
	private int jumpTime = 0;
	private boolean goRight = false;
	private boolean goLeft = false;
	private boolean run = false;
	private boolean playerJumps = false;
	private boolean big = false;
	private boolean fireBall = false;
	private boolean invincible = false;
	private int invincibleTime = 0;
	private boolean flashing = false;
	private int flashingTime = 0;
	
	private ImageView imageA;
	private ImageView imageAInverted;
	private ImageView imageB;
	private ImageView imageBInverted;
	private ImageView imageALeft;
	private ImageView imageALeftInverted;
	private ImageView imageBLeft;
	private ImageView imageBLeftInverted;
	private ImageView imageToBeShown;

	
	public Player () {

		super(50, 50);
		this.xAbsolutValue = 50;
		setSpeed2(5);
		setFallSpeed(9);
		
		imageA = new ImageView(new Image("/media/GubbeA.png"));
			 imageToBeShown = imageA;
			 imageAInverted = ImageModelling.invertImage(imageA);
			 imageALeft = ImageModelling.flipImageHorizontally(imageA);
			 imageALeftInverted = ImageModelling.invertImage(imageALeft);
			
		imageB = new ImageView(new Image("/media/GubbeB.png"));
			 imageBInverted = ImageModelling.invertImage(imageB);
			 imageBLeft = ImageModelling.flipImageHorizontally(imageB);
			 imageBLeftInverted = ImageModelling.invertImage(imageBLeft);

		
		changeImage();
		 
	}
	
	
	public int getXAbsolutValue() {
		return this.xAbsolutValue;
	}
	
	public void setXAbsolutValue(int change) {
		if(change > 0 || change < 0) {
			secondLatestXInSpace = this.getXAbsolutValue();
			this.xAbsolutValue = this.xAbsolutValue + change;
		}
	}
	
	
	public int getSecondLatestXAbsolutValue() {
		return secondLatestXAbsolutValue;
	}
	
	
	public boolean getGoRight() {
		return goRight;
	}
	
	public void setGoRight(boolean b) {
		goRight = b;
	}
	
	
	public boolean getGoLeft() {
		return goLeft;
	}
	
	public void setGoLeft(boolean b) {
		goLeft = b;
	}
	
	public boolean getPlayerJumps() {
		return playerJumps;
	}
	
	public void setPlayerJumps(boolean b) {
		playerJumps = b;
	}
	
	public boolean isFlashing() {
		return flashing;
	}

	public void setFlashing(boolean b) {
		this.flashing = b;
	}

	public int getFlashingTime() {
		return flashingTime;
	}

	public void setFlashingTime(int change) {
		this.flashingTime = this.flashingTime + change;
	}

	public boolean isInvincible() {
		return invincible;
	}

	public void setInvincible(boolean b) {
		this.invincible = b;
	}

	public int getInvincibleTime() {
		return invincibleTime;
	}

	public void setInvincibleTime(int change) {
		this.invincibleTime = this.invincibleTime + change;
	}
	
	public boolean getRun() {
		return run;
	}
	
	public void setRun(boolean b) {
		run = b;
	}
	
	public boolean getBig() {
		return this.big;
	}
	
	public boolean getFireBall() {
		return fireBall;
	}
	
	public void setFireBall(boolean b) {
		fireBall = b;
	}
	
	
	public void setBig() {
		if(getBig() == false) {
			this.big = true;
			setYValue(-50);
			
			changeImage();
		}
	}
	

	
	public void setSmall() {
		if(getBig()) {
			this.big = false;
			setYValue(50);
			
			changeImage();
		}
	}
		
	
	public boolean directionLeft() {
		if(getXAbsolutValue() - secondLatestXInSpace > 0) {
			return false;
		}
		return true;
	}
	
	
	public int getJumpTime() {
		return this.jumpTime;
	}
	
	public void setJumpTime(int jumpTime) {
		this.jumpTime = jumpTime;
	}


	
	@Override
	public void setXValue(int change) {
		
		this.setXAbsolutValue(change);
		
		super.setXValue(change);
		xWiseChangeSpeed[xWiseChangeSpeedHelp % 4] = change;
		xWiseChangeSpeedHelp++;
	}
	
	
	public void savexWiseChange() {
		
		int change = getXAbsolutValue() - this.secondLatestXInTime;
		xWiseChangeSpeed[xWiseChangeSpeedHelp % 4] = change;
		xWiseChangeSpeedHelp++;
		this.secondLatestXInTime = getXAbsolutValue();
	}
	
	
	public int xWiseChange(){
		
		int summa = 0;
		for(int i = 0; i < 4; i++) {
			summa = summa + xWiseChangeSpeed[i];
		}
		return summa;
	}
	
	
	@Override
	public void fall(int change) {
		
		if(this.getYValue() + getHeight() < 650){
			setYValue(Math.min(getFallSpeed(), change));
		}
	}
	
	
	
	// The player's jump height changes depending on the speed it has. Or more precisely, depending
	// on how far it has moved in xDirection during the four latest updates of the screen. 
	
	public void jump(int change) {  
		
		if(xWiseChange() > 20 || xWiseChange() < -20) {
			setYValue(Math.max(change, -25));
		}
		
		else if(xWiseChange() > 10 || xWiseChange() < -10) {
			setYValue(Math.max(change, -17));
		}
		else {
			setYValue(Math.max(change, -10));
		}
		if(change == 0) {
			setPlayerJumps(false);
			setJumpTime(0);
		}
	}
	
	
	
	public void  changeJumpTime() {
		   
		if(this.jumpTime <= 0) {
			setPlayerJumps(false);
		}
		else {
			this.jumpTime = this.jumpTime - 1;
		}
	}
	

	
	
	@Override
	public void changeImage(){
		
		chooseImage();
		
		imageToBeShown.setFitHeight(50);
		 if(this.big) {
				imageToBeShown.setFitHeight(100);
			}
		 imageToBeShown.setFitWidth(50);
		 imageToBeShown.setPreserveRatio(false);
		 imageToBeShown.setX(getXValue());
		 imageToBeShown.setY(getYValue());
	}

	
	public void chooseImage() {
		
		if( getXAbsolutValue() % 2 == 0) {
			imageToBeShown = imageA;
			if(getFlashingTime() > 0 && getFlashingTime() % 3 == 0) {
				imageToBeShown = imageAInverted;
			}
			if(directionLeft()) {
				imageToBeShown = imageALeft;
				if(getFlashingTime() > 0 && getFlashingTime() % 3 == 0) {
					imageToBeShown = imageALeftInverted;
				}
			}
		}
		else {
			imageToBeShown = imageB;
			if(getFlashingTime() > 0 && getFlashingTime() % 3 == 0) {
				imageToBeShown = imageBInverted;
			}
			if(directionLeft()) {
				imageToBeShown = imageBLeft;
				if(getFlashingTime() > 0 && getFlashingTime() % 3 == 0) {
					imageToBeShown = imageBLeftInverted;
				}
			}
		} 
	}
	

	
	@Override
	public int getHeight() {
		return (int) imageToBeShown.getFitHeight();	
	}
	
	
	@Override
	public int getWidth() {
		return (int) imageToBeShown.getFitWidth();
	}
	
	@Override
	public ImageView getImageView() {
		return imageToBeShown;
	}


}
