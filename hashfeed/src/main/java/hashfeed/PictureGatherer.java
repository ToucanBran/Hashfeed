package hashfeed;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class PictureGatherer implements Runnable{

	private String[] hashtags;
	
	public PictureGatherer(String[] tags)
	{
		setTags(tags);
	}
	
	public void run() {
		
		try {
			gatherPics(getTags());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void setTags(String[] tags)
	{
		hashtags = tags;
	}
	private String[] getTags()
	{
		return hashtags;
	}
	
	public void gatherPics(String[] hashtags) throws IOException
	{
		//loops through hashtags and gathers picture sources
		for (int i = 0; i < hashtags.length;i++)
		{
			//Jsoup connects to the tag page using the given hashtag
			String url = "https://www.instagram.com/explore/tags/" + hashtags[i] + "/";
			String htmlText = Jsoup.connect(url).get().html();
			String jsonString = getJsonString(htmlText);
			
			Gson gson = new Gson();
			
			//puts the json string into a POJO
			Response response = gson.fromJson(jsonString, Response.class);
			
			//instaMedia nodes contain the picture caption and source. In the future
			//I'll also harn the user ID
			List<Nodes> instaMedia = response.entry_data.TagPage.get(0).tag.media.getNodes();
			
			//loops through each node and prints the source. In the future, I'll use
			//Jsoup to download the picture at the given source
			for (int j = 0; j < instaMedia.size() ;j++)
			{
				//download pictures to folder
				URL picSrc = new URL(instaMedia.get(j).getDisplay_src());
				String baseFile = "C:/Users/Owner/Documents/wedpics/";
				String pictureName = instaMedia.get(j).getCode();
				String pictureExt = ".png";
				
				File destination = new File(baseFile + pictureName + pictureExt);
				FileUtils.copyURLToFile(picSrc, destination);
			}
		}
	
	}
	
	public String getJsonString(String html)
	{
		//the window._sharedData variable in the html text holds the json string
		String[] shards = html.split("window._sharedData = ");
		String[] instaJSON = shards[1].split(";</script");
		
		//not sure why I have to put it into a JsonElement, I'll have to investigate
		//why I did this
		JsonElement jElement = new JsonParser().parse(instaJSON[0]);
		JsonObject jObject = jElement.getAsJsonObject();
		String jsonString = jObject.toString();
		
		return jsonString;
	}


}
