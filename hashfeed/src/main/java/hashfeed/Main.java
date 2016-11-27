package hashfeed;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

 public class Main extends Application{

	
	public static void main(String[] args) throws IOException{
		launch(args);
		
		
		}
	@Override
	public void start(Stage arg0) throws Exception {

		String[] hashtags = {"poker"};
		Thread gp = new Thread(new PictureGatherer(hashtags));
		gp.start();
		
		
		
	}
	
	

	
 }