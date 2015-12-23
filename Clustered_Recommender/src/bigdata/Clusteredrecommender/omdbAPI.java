package bigdata.Clusteredrecommender;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class omdbAPI {
	//private String movie_name;
	private Map<String, String> map;
	public omdbAPI(String movie_name) throws NotfindExcetion{

		//this.movie_name=movie_name;
		try{
			InputStream input = new URL("http://www.omdbapi.com/?t=" + URLEncoder.encode(movie_name, "UTF-8")).openStream();
			map = new Gson().fromJson(new InputStreamReader(input, "UTF-8"), new TypeToken<Map<String, String>>(){}.getType());
			if(map.get("Response").equals("False"))
				throw (new NotfindExcetion(movie_name));
	
		}
		catch(JsonIOException | JsonSyntaxException | IOException e){ System.err.println(e.getMessage());}
	}
	
	public BufferedImage getPoster(){
		String poster = map.get("Poster");
		BufferedImage img;
		try{
			img = ImageIO.read(new URL(poster));
			//System.out.println(img.toString());
			return img;
		}
		catch(Exception e){e.getMessage();}
		return null;
	}
	
	public String getIMDBScore(){
		return map.get("imdbRating");
	}
	
	public String getYear(){
		return map.get("Year");
	}
	
	public String getIMDBID(){
		return map.get("imdbID");
	}
	
	public String getPlot(){
		return map.get("Plot");
	}
	
	public String getName(){
		return map.get("Title");
	}
	
}
