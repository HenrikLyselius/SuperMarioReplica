package screenObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CrushedBlock extends ScreenObject2	implements ScreenObject{
	
	private ImageView block;
	private int type;
	
	
	public CrushedBlock(int x, int y, int i) {
		
		super(x, y);

		this.type = i;
		
		if(type == 0) {
			this.block = new ImageView(new Image("/media/LitenKloss1.png"));
		}
		
		if(type == 1) {
			this.block = new ImageView(new Image("/media/LitenKloss2.png"));
		}
		
		if(type == 2) {
			this.block = new ImageView(new Image("/media/LitenKloss4.png"));
		}
		
		if(type == 3) {
			this.block = new ImageView(new Image("/media/LitenKloss3A.png"));
		}
		
		changeBlock();
	}


	
	public ImageView getImageView() {
		return block;
	}
	
	public void animation() {
		if(type == 0) {
			setXValue(-5);
			setYValue(-5);
		}
		
		if(type == 1) {
			setXValue(5);
			setYValue(-5);
		}
		
		if(type == 2) {
			setXValue(5);
			setYValue(5);
		}
		
		if(type == 3) {
			setXValue(-5);
			setYValue(5);
		}
		
		changeBlock();
		setAnimationTime(-1);
	}

	
	public void changeBlock() {
		if(type == 0) {
			block.setX(getXValue()); 
			block.setY(getYValue()); 
			block.setFitWidth(25); 
			block.setFitHeight(25); 
			
		}
		
		if(type == 1) {
			block.setX(getXValue() + 25); 
			block.setY(getYValue()); 
			block.setFitWidth(25); 
			block.setFitHeight(25); 
		
		}
		
		if(type == 2) {
			block.setX(getXValue() + 25); 
			block.setY(getYValue() + 25); 
			block.setFitWidth(25); 
			block.setFitHeight(25); 
		
		}
		
		if(type == 3) {
			block.setX(getXValue()); 
			block.setY(getYValue() + 25); 
			block.setFitWidth(25); 
			block.setFitHeight(25); 
		}
	}



}
