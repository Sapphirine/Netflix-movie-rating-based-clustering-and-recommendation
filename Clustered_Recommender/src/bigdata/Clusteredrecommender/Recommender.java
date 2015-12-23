package bigdata.Clusteredrecommender;
import java.io.*;
import java.util.*;

public class Recommender {
	private static Hashtable<Integer, String> movieList = new Hashtable<Integer, String>();
	private static Hashtable<String,Integer> revmovieList = new Hashtable<String,Integer>();
	@SuppressWarnings("unchecked")
	private static HashSet<Integer>[] clusterInfo = (HashSet<Integer>[])new HashSet<?>[20];
	private static Hashtable<Integer, Double> eval = new Hashtable<Integer, Double>();
	
	public void initialize(){
		try{
			BufferedReader dr1 = new BufferedReader(
				new InputStreamReader(new FileInputStream(new File("movie_clustered.txt"))));
			String s1;
			for(int i=0;i<20;i++)
				clusterInfo[i] = new HashSet<Integer>();
			while((s1=dr1.readLine())!=null){
    			String[] sepdata = s1.split(",");
    			Integer id = Integer.valueOf(sepdata[0]);
    			movieList.put(id, sepdata[4]);
    			revmovieList.put(sepdata[4], id);
    			clusterInfo[Integer.parseInt(sepdata[5])].add(id);
    			eval.put(id, Double.valueOf(sepdata[6]));
    		}
			dr1.close();
		}
		catch(IOException e){
			System.err.println(e.getMessage());
		}
		
	}
	
	public Recommender(){
		initialize();
	}
	
	public boolean isExist(String movie){
		return revmovieList.containsKey(movie);
	}
	
	public List<String> getRec(String movie){
		List<String> recMovieList = new ArrayList<String>();
		if(revmovieList.containsKey(movie)){
			Integer id = revmovieList.get(movie);
			for(HashSet<Integer> s:clusterInfo){
				if(s.contains(id)){
					for(Integer i:recList(s,id))
						recMovieList.add(movieList.get(i));
					break;
				}
			}
		}
		return recMovieList;
	}
	
	private List<Integer> recList(HashSet<Integer> s,Integer targetID){
		double val = eval.get(targetID).doubleValue();
		List<Integer> recMovieID = new ArrayList<Integer>();
		double max=0,delta;
		Integer maxID = new Integer(0);
		for(Integer i:s){
			if(!i.equals(targetID)){
				if(recMovieID.size()<=4){
					recMovieID.add(i);
					if(recMovieID.size()==5){
						for(Integer rec:recMovieID){
							delta = Math.abs(eval.get(rec).doubleValue()-val);
							if(delta>max){
								max=delta;
								maxID=rec;
							}
						}
					}
				}
				else{
					delta = Math.abs(eval.get(i).doubleValue()-val);
					if(delta<max){
						recMovieID.remove(maxID);
						recMovieID.add(i);
						max=0;
						for(Integer rec:recMovieID){
							delta = Math.abs(eval.get(rec).doubleValue()-val);
							if(delta>max){
								max=delta;
								maxID=rec;
							}
						}
					}
				}
			}
		}
		return recMovieID;
	}
}
