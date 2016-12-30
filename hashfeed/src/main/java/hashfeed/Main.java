package hashfeed;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.Animation;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


 public class Main extends Application{

	
	public static void main(String[] args) throws IOException{
		launch(args);
		
		
		}
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//load fxml for main screen
		FXMLLoader loader = new FXMLLoader(getClass().getResource("HashFeedScene.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
	    primaryStage.setScene(scene);
	    primaryStage.show();
	    
	    
	   

	}
	    
	
}

