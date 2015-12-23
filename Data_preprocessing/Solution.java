import java.io.*;
import java.text.DecimalFormat;

public class Solution {
	public static void main(String args[]){
		final File folder = new File("/Users/samluo/Downloads/download/training_set");
		listFilesForFolder(folder);
	}

	static public void listFilesForFolder(final File folder) {
		String s,filename;
		int movie_number,score,count;
		BufferedReader bfreader;
	    for (final File fileEntry : folder.listFiles()) {
	    	filename = fileEntry.getName();
	    	if(!filename.substring(0, 2).equals("mv"))
	    		continue;
	    	movie_number = Integer.parseInt(filename.substring(3,10));
	    	System.out.print(movie_number+",");
	    	try{
	    		bfreader = new BufferedReader(new InputStreamReader(new FileInputStream(fileEntry)));
	    		bfreader.readLine();
	    		score=0;
	    		count=0;
	    		while((s=bfreader.readLine())!=null){
	    			String[] sepdata = s.split(",");
	    			score += Integer.parseInt(sepdata[1]);
	    			count++;
	    		}
	    		DecimalFormat decimalFormat=new DecimalFormat(".0");
	    		System.out.print(decimalFormat.format((float)score/count));
	    		System.out.print(",");
	    		System.out.println(count);
	    	}
	    	catch(Exception e){}
	    }
	}
}