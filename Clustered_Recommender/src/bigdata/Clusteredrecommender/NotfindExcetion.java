package bigdata.Clusteredrecommender;

public class NotfindExcetion extends Exception{
	private static final long serialVersionUID = 8233369098417701412L;
	private String movie_name;
	NotfindExcetion(String moviename){
		movie_name=moviename;
	}
	
	@Override
	public String toString(){
		return (movie_name+" did not found in OMDB.");
	}
}
