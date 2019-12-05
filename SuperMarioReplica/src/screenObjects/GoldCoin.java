package screenObjects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class GoldCoin extends ScreenObject2 implements ScreenObject{
	
	private Rectangle rectangle;
	
	public GoldCoin(int x, int y) {
		super(x, y);
		rectangle = new Rectangle();
		changeRectangle();
	}
	

	@Override
	public void animation() {
		setYValue(-3);
		changeRectangle();
		setAnimationTime(-1);
	}
	
	
	public void changeRectangle() {
		rectangle.setX(getXValue() + 22); 
		rectangle.setY(getYValue() - 40); 
		rectangle.setWidth(5); 
		rectangle.setHeight(50); 
		rectangle.setFill(Color.YELLOW);
		rectangle.setStroke(Color.YELLOW);
	}
	
	
	@Override
	public Shape getObject() {
		return rectangle;
	}


}
