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
private StackPane root = new StackPane();
private SequentialTransition slideshow;
	
	public StackPane getRoot()
	{
		return root;
	}
	
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
	public void start() {
		slideshow = new SequentialTransition();
		
		File[] pictureFiles = new File("main/resources/").listFiles();
		
		for (File file : pictureFiles )
		{
			SequentialTransition sequentialTransition = new SequentialTransition();
			Image image = new Image(file.toURI().toString());
			ImageView slide = new ImageView(image);
			slide.setPreserveRatio(true);
			slide.setFitHeight(750);
			FadeTransition fadeIn = getFadeTransition(slide, 0.0, 1.0, 2000);
	        PauseTransition stayOn = new PauseTransition(Duration.millis(4000));
	        FadeTransition fadeOut = getFadeTransition(slide, 1.0, 0.0, 2000);
	        
	        sequentialTransition.getChildren().addAll(fadeIn, stayOn, fadeOut);
	        slide.setOpacity(0);
	        root.getChildren().add(slide);
	       slideshow.getChildren().add(sequentialTransition);
		}
		
		slideshow.play();
		}
	}