import java.io.*;
//import java.text.DecimalFormat;

public class movie_clustered {
	public static void main(String args[]){
		final File data1 = new File("movie_data.csv");
		final File data2 = new File("out.txt");
		final File data3 = new File("movie_titles.txt");
		String s1,s2,s3;
		try{
			BufferedReader dr1 = new BufferedReader(new InputStreamReader(new FileInputStream(data1)));
			BufferedReader dr2 = new BufferedReader(new InputStreamReader(new FileInputStream(data2)));
			BufferedReader dr3 = new BufferedReader(new InputStreamReader(new FileInputStream(data3)));
			BufferedWriter bfwriter = new BufferedWriter(new FileWriter(new File("movie_clustered.txt")));
			while((s1=dr1.readLine())!=null && (s2=dr2.readLine())!=null && (s3=dr3.readLine())!=null){
	    			String[] sepdata2 = s2.split(" ");
	    			String[] sepdata3 = s3.split(",");
	    			bfwriter.write(s1+","+sepdata3[2]+","+sepdata2[sepdata2.length-1]+","+sepdata2[3]+"\n");
	    		}
	    	dr1.close();
	    	dr2.close();
	    	dr3.close();
	    	bfwriter.close();
		}
		catch(Exception e){}
	}
}