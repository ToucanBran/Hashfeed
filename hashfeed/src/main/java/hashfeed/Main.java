package hashfeed;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;





import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.stage.Stage;


 public class Main extends Application{

	
	public static void main(String[] args) throws IOException{
		launch(args);
		
		
		}
	@Override
	public void start(Stage primaryStage) throws Exception {
		String[] hashtags = {"fitness"};
		//Thread gp = new Thread(new PictureGatherer(hashtags));
		//gp.start();
		final SimpleSlideShow simpleSlideShow = new SimpleSlideShow();
		Scene scene = new Scene(simpleSlideShow.getRoot());
	
	    primaryStage.setScene(scene);
	    primaryStage.show();
	    
	    Timer timer = new Timer();
	    
	    //fix this. It resets the slideshow images
	    timer.schedule(new TimerTask(){

			@Override
			public void run() {
				Platform.runLater(new Runnable(){

					public void run() {
						// TODO Auto-generated method stub
						System.out.println("starting...");
						simpleSlideShow.start();
					}
					
				});
			}
	    	
	    },10000,13000);
	    
	   
		
		
	}	
	}

