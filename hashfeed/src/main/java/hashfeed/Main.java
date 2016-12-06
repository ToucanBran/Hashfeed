package hashfeed;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.Animation;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;


 public class Main extends Application{

	
	public static void main(String[] args) throws IOException{
		launch(args);
		
		
		}
	@Override
	public void start(Stage primaryStage) throws Exception {
		String[] hashtags = {"fitness"};
		final Thread gp = new Thread(new PictureGatherer(hashtags));
		gp.start();
		final SimpleSlideShow simpleSlideShow = new SimpleSlideShow();
		Scene scene = new Scene(simpleSlideShow.getRoot());
		primaryStage.setMaximized(true);
	    primaryStage.setScene(scene);
	    simpleSlideShow.getRoot().setStyle("-fx-background-color: black");
	    primaryStage.show();
	    
	    Timer timer = new Timer();
	    simpleSlideShow.start();
	 
	    
	   timer.schedule(new TimerTask(){

			@Override
			public void run() {
				Platform.runLater(new Runnable(){

					public void run() {
						// TODO Auto-generated method stub
						if (simpleSlideShow.getSlideShow().getStatus() != Animation.Status.RUNNING){
							   System.out.println("Starting...");
							   simpleSlideShow.start();
							   System.out.println(gp.isAlive());
						}
					}
					
				});
			}
	    	
	    },1000,1000);
	   
		
		
	}	
	}

