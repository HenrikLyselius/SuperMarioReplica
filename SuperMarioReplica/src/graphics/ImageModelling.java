package graphics;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class ImageModelling {

	
	public static ImageView invertImage(ImageView original) {
		
		Image originalImage = original.getImage();
		PixelReader pr = originalImage.getPixelReader();
		WritableImage newImage = new WritableImage((int) originalImage.getWidth(), (int) originalImage.getHeight());
		PixelWriter pw = newImage.getPixelWriter();
		
		for(int y = 0; y < originalImage.getHeight(); y++) {
			for(int x = 0; x < originalImage.getWidth(); x++) {
				Color color = pr.getColor(x, y);
				color = color.invert();
				pw.setColor(x, y, color);
			}
		}
		
		return new ImageView((Image)newImage);
	}
	
	
	public static ImageView flipImageHorizontally(ImageView original) {
		
		Image originalImage = original.getImage();
		int width = (int) originalImage.getWidth();
		int height = (int) originalImage.getHeight();
		PixelReader pr = originalImage.getPixelReader();
		WritableImage newImage = new WritableImage(width, height);
		PixelWriter pw = newImage.getPixelWriter();
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				Color color = pr.getColor(x, y);
				pw.setColor((width - 1) - x, y, color);
			}
		}
		
		return new ImageView((Image)newImage);
	}
}
