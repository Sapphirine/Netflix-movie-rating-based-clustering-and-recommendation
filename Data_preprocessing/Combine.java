import java.io.*;
//import java.text.DecimalFormat;

public class Combine {
	public static void main(String args[]){
		final File data1 = new File("movie_titles.txt");
		final File data2 = new File("averate.txt");
		String s1,s2;
		try{
			BufferedReader dr1 = new BufferedReader(new InputStreamReader(new FileInputStream(data1)));
			BufferedReader dr2 = new BufferedReader(new InputStreamReader(new FileInputStream(data2)));
			while((s1=dr1.readLine())!=null && (s2=dr2.readLine())!=null){
	    			String[] sepdata1 = s1.split(",");
	    			String[] sepdata2 = s2.split(",");
	    			System.out.println(sepdata1[0]+","+sepdata1[1]+","+sepdata2[1]+","+sepdata2[2]);
	    		}
	    	dr1.close();
	    	dr2.close();
		}
		catch(Exception e){}
	}
}