package hashfeed;

import java.io.File;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class SimpleSlideShow {

//object on which the slideshow will be placed onto
private StackPane root = new StackPane();

//This object will play the slideshow of images
private SequentialTransition slideshow;
	
	public StackPane getRoot()
	{
		return root;
	}
	
	//I copied this code from a stackoverflow question but 
	//it pretty much creates a fade on each image
	public FadeTransition getFadeTransition(ImageView imageView, double fromValue, double toValue, int durationInMilliseconds) {

	    FadeTransition ft = new FadeTransition(Duration.millis(durationInMilliseconds), imageView);
	    ft.setFromValue(fromValue);
	    ft.setToValue(toValue);

	    return ft;

	  }

	public SequentialTransition getSlideShow()
	{
		return slideshow;
	}
	
	/*
	 * I also copied some of this from a stackoverflow question but
	 * here's the gist of it: this function will do the following:
	 * 		-put all the files into an array
	 * 		-Creates two sequential transition objects:
	 * 			*One to play the fade in/fade out to black
	 *          *One to display the image and then display
	 *          sequentialtransition which plays the fade
	 * 		
	 */
	public void start() {
		//this object displays the image and the fade
		slideshow = new SequentialTransition();
		
		//puts pictures into file array
		File[] pictureFiles = new File("main/resources/").listFiles();
		
		//adds all the files to the slideshow
		for (File file : pictureFiles )
		{
			//This object is specifically for fading in and out
			SequentialTransition sequentialTransition = new SequentialTransition();
			
			//stores files into images
			Image image = new Image(file.toURI().toString());
			ImageView slide = new ImageView(image);
			
			//had to do this because otherwise the picture
			//would either look funny or be way too large
			slide.setPreserveRatio(true);
			slide.setFitHeight(700);
			
			//fade images
			FadeTransition fadeIn = getFadeTransition(slide, 0.0, 1.0, 2000);
	        PauseTransition stayOn = new PauseTransition(Duration.millis(4000));
	        FadeTransition fadeOut = getFadeTransition(slide, 1.0, 0.0, 2000);
	        
	        //adds the different fades to the fade sequentialtransition
	        sequentialTransition.getChildren().addAll(fadeIn, stayOn, fadeOut);
	        slide.setOpacity(0);
	        
	        //adds the image slide to the stack pane
	        root.getChildren().add(slide);
	        
	        //adds fades to the slideshow
	        slideshow.getChildren().add(sequentialTransition);
		}
		
		slideshow.play();
		}
	}