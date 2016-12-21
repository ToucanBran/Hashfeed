package hashfeed;

import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.Animation;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainController {
	
	@FXML TextField userInput;
	@FXML Button goButton;
	private String[] hashtags;
	private SimpleSlideShow simpleSlideShow;
	private Scene scene; 
	private Stage stageRef, newStage;
	
	public void buttonPushed()
	{
		hashtags = userInput.getText().split(",");
		
		final Thread gp = new Thread(new PictureGatherer(hashtags));
		gp.setDaemon(true);
		gp.start();
		setupNewStage();
		runSlideShow(simpleSlideShow);
	}
	
	public void setupNewStage()
	{
		simpleSlideShow = new SimpleSlideShow();
		scene = new Scene(simpleSlideShow.getRoot());
		newStage = new Stage();
		newStage.setScene(scene);
		simpleSlideShow.getRoot().setStyle("-fx-background-color: black");
		
		stageRef = (Stage) goButton.getScene().getWindow();
		stageRef.close();
		newStage.setMaximized(true);
		
		newStage.show();
		newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	        
	        public void handle(WindowEvent e) {
	           Platform.exit();
	           System.exit(0);
	        }
	     });
		
	}
	/*
	 * runSlideShow: function 
	 * 
	 */
	public void runSlideShow(final SimpleSlideShow simpleSlideShow)
	{
		Timer timer = new Timer();
	    simpleSlideShow.start();
	 	    
	    timer.schedule(new TimerTask(){

			@Override
			public void run() {
				Platform.runLater(new Runnable(){

					public void run() {
						// TODO Auto-generated method stub
						if (simpleSlideShow.getSlideShow().getStatus() != Animation.Status.RUNNING){
							   simpleSlideShow.start();
						}
					}
					
				});
			}
	    	
	    },1000,1000);
	   
		
		
	}	

}
