import java.io.*;
import java.text.DecimalFormat;

public class Getall {
	public static void main(String args[]){
		final File folder = new File("/Users/samluo/Downloads/download/training_set");
		listFilesForFolder(folder);
	}

	static public void listFilesForFolder(final File folder) {
		String s,filename;
		int movie_number,count=0;
		BufferedReader bfreader;
		BufferedWriter[] bfwriter = new BufferedWriter[10];
		try{
		for(int i=0;i<10;i++)
			bfwriter[i] = new BufferedWriter(new FileWriter(new File(i+".txt")));
	    for (final File fileEntry : folder.listFiles()) {
	    	filename = fileEntry.getName();
	    	if(!filename.substring(0, 2).equals("mv"))
	    		continue;
	    	movie_number = Integer.parseInt(filename.substring(3,10));
	    	System.out.println(movie_number);
	    	try{
	    		bfreader = new BufferedReader(new InputStreamReader(new FileInputStream(fileEntry)));
	    		bfreader.readLine();
	    		while((s=bfreader.readLine())!=null){
	    			String[] sepdata = s.split(",");
	    			int fn = Integer.parseInt(sepdata[0])/265000;
	    			bfwriter[fn].write(sepdata[0]+","+movie_number+","+sepdata[1]+"\n");
	    		}
	    		//DecimalFormat decimalFormat=new DecimalFormat(".0");
	    		//count++;
	    		//if(count == 2)
	    		//	break;
	    	}
	    	catch(Exception e){}
	    }
	    for(int i=0;i<10;i++)
	    	bfwriter[i].close();
		}
		catch(Exception e){}
	}
}