import java.io.*;
import java.util.*;
//import java.text.DecimalFormat;

public class Sepbyyear {
	public static void main(String args[]){
		final File data1 = new File("movie_titles.txt");
		final File data2 = new File("recommender.txt");
		Hashtable<String,Integer> movielist = new Hashtable<String,Integer>();
		BufferedWriter[] bfwriter = new BufferedWriter[3];
		String s1,s2;
		try{
			BufferedReader dr1 = new BufferedReader(new InputStreamReader(new FileInputStream(data1)));
			while((s1=dr1.readLine())!=null){
				String[] sepdata = s1.split(",");
				if(!sepdata[1].equals("NULL"))
    				movielist.put(sepdata[0],Integer.parseInt(sepdata[1]));
    			else
    				movielist.put(sepdata[0],new Integer(1800));
    			//System.out.println(sepdata[0]+" "+sepdata[1]);
			}
	    	dr1.close();
		}
		catch(Exception e){}
		
		try{
			BufferedReader dr2 = new BufferedReader(new InputStreamReader(new FileInputStream(data2)));
			bfwriter[0] = new BufferedWriter(new FileWriter(new File("1990.txt")));
			bfwriter[1] = new BufferedWriter(new FileWriter(new File("2000.txt")));
			bfwriter[2] = new BufferedWriter(new FileWriter(new File("2005.txt")));
			while((s2=dr2.readLine())!=null){
	    			String[] sepdata2 = s2.split(",");
	    			int i = movielist.get(sepdata2[1]);
	    			//System.out.println(sepdata2[1]+" "+i);
	    			if(i<=1990){
	    				System.out.println(s2+" To set 1");
	    				bfwriter[0].write(s2+"\n");
	    			}
	    			else if (i<=2000){
	    				//System.out.println(s2+" To set 2");
	    				bfwriter[1].write(s2+"\n");
	    			}
	    			else{
	    				//System.out.println(s2+" To set 3");
	    				bfwriter[2].write(s2+"\n");
	    			}
	    		}
	    	dr2.close();
	    	bfwriter[0].close();
	    	bfwriter[1].close();
	    	bfwriter[2].close();
		}
		catch(Exception e){}
	}
}