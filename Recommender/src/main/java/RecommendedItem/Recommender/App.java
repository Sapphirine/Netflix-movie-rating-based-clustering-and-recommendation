package RecommendedItem.Recommender;
import java.io.*;
import java.util.*;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.common.Weighting;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.*;
import org.apache.mahout.cf.taste.impl.recommender.*;
import org.apache.mahout.cf.taste.impl.recommender.knn.*;
//import org.apache.mahout.cf.taste.impl.recommender.svd.*;
import org.apache.mahout.cf.taste.impl.similarity.*;
//import org.apache.mahout.cf.taste.impl.similarity.SpearmanCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.*;
import org.apache.mahout.cf.taste.similarity.*;

public class App 
{
	@SuppressWarnings("unchecked")
    private static Hashtable<Integer,String>[] movielist = (Hashtable<Integer,String>[])new Hashtable<?,?>[3];
	private static List<String> userinfo = new ArrayList<String>();
	private static int current,set;
	private static Random rand = new Random();
	public static void loadmoviedata(){
		try {
			BufferedReader bfreader = new BufferedReader(new InputStreamReader(new FileInputStream("/data/movie_titles.txt")));
			String s;
			movielist[0] = new Hashtable<Integer,String>();
			movielist[1] = new Hashtable<Integer,String>(); 
			movielist[2] = new Hashtable<Integer,String>(); 
			while((s=bfreader.readLine())!=null){
    			String[] sepdata = s.split(",");
    			if(!sepdata[1].equals("NULL")){
    				int year=Integer.parseInt(sepdata[1]);
    				if(year<=1990)
    					movielist[0].put(Integer.parseInt(sepdata[0]), sepdata[2]);
    				if(year<=2000)
    					movielist[1].put(Integer.parseInt(sepdata[0]), sepdata[2]);
    				else
    					movielist[2].put(Integer.parseInt(sepdata[0]), sepdata[2]);
    			}
    			else
    				movielist[0].put(Integer.parseInt(sepdata[0]), sepdata[2]);
    		}
			bfreader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void storeUserInfo(int i){
		userinfo.add("1,"+current+","+i);
		System.out.println("Movie: "+movielist[set].get(current)+", score: "+i);
	}
	
	public static void setSelectSet(int i){
		if(i>=0 && i<=2)
			set=i;
	}
	
	public static String getRandomMovieName(){

		do{
			int value = rand.nextInt(17770); 
			if(movielist[set].containsKey(value)){
				current=value;
				return movielist[set].get(value);
			}
		}while(true);
	}
	
	public static void recmain() throws Exception {
		Thread.sleep(100);
		BufferedWriter bfwriter = new BufferedWriter(new FileWriter("/data/temp.dat"));
		for(String s:userinfo)
			bfwriter.write(s+"\n");
		bfwriter.close();
		Runtime.getRuntime().exec("/data/cat.sh -b "+"/data/set"+set+".txt");
		//final File moviedata = new File("/Users/samluo/Downloads/download/recc.dat");
		DataModel model = new FileDataModel (new File("/data/recc.dat"));
		UserSimilarity similarity = new PearsonCorrelationSimilarity (model,Weighting.WEIGHTED);
		UserNeighborhood neighborhood = new NearestNUserNeighborhood (100, similarity, model);
		Recommender recommender = new GenericUserBasedRecommender (model, neighborhood, similarity);
		List<RecommendedItem> recommendations = recommender.recommend(1, 5);
		for (RecommendedItem recommendation : recommendations) {
			String moviename = movielist[set].get((int)recommendation.getItemID());
			System.out.println("Recommended movie: "+moviename);
		}
		Runtime.getRuntime().exec("/data/cat.sh -c");
	}
}
