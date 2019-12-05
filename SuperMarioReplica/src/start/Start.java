package start;

import graphics.GameView;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.Stage;

public class Start extends Application{

	public static void main(String[] args) {
		
		GameView gv = new GameView();
		gv.startApplication();

	}
	
	@Override
    public void start(Stage primaryStage) throws Exception {
    }

}
