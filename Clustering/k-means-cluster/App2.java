import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.mahout.clustering.Cluster;
import org.apache.mahout.clustering.classify.WeightedPropertyVectorWritable;
import org.apache.mahout.clustering.kmeans.KMeansDriver;
import org.apache.mahout.clustering.kmeans.Kluster;
import org.apache.mahout.common.distance.EuclideanDistanceMeasure;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.VectorWritable;

import java.io.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App2 {
	public static void main(String[] args)throws Exception{
		//Pre-process the data file to 2-dimensional array
		
		File file = new File("/home/bigdata/extend/final_proj/movie_data.txt");
        BufferedReader reader2 = null;
        double[][] points=new double[17770][3];
        try {
        	reader2 = new BufferedReader(new FileReader(file));
            String tempString = null;
            tempString = reader2.readLine();
            int line = 0;
            
            
            while ((tempString = reader2.readLine()) != null) {
                String[] tempTokens=tempString.split(",");
                
                for(int i=0;i<3;i++){
                	points[line][i]=Double.parseDouble(tempTokens[i+1]);
                }
                
                //System.out.println(tempTokens[0]+" "+Double.parseDouble(tempTokens[1]));
                line++;
            }
            
            reader2.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader2 != null) {
                try {
                    reader2.close();
                } catch (IOException e1) {
                }
            }
        }
        //System.out.println(points[17769][1]);
        //Start officially
       
        /* 
		double[][] points = {
	            {1, 1}, {2, 1}, {1, 2},
	            {2, 2}, {3, 3}, {8, 8},
	            {9, 8}, {8, 9}, {9, 9}};
	            */
        int k=20;
        List<Vector> vectors=getPoints(points);
        
        File testData=new File("/home/bigdata/extend/final_proj/testData");
        
        if(!testData.exists()){
        	testData.mkdir();
        }
        
        Configuration conf=new Configuration();
        FileSystem fs=FileSystem.get(conf);
        writePointsToFile(vectors,"/home/bigdata/extend/final_proj/testData/points/file1",fs,conf);
        
        Path path=new Path("/home/bigdata/extend/final_proj/testData/clusters/part-00000");
        
		SequenceFile.Writer writer=new SequenceFile.Writer(fs, conf, path, Text.class, Kluster.class);
        for(int i=0;i<k;i++){
        	Vector vec=vectors.get(i);
        	Kluster cluster=new Kluster(vec,i,new EuclideanDistanceMeasure());
        	writer.append(new Text(cluster.getIdentifier()), cluster);
        }
        writer.close();
        
        KMeansDriver.run(conf, new Path("/home/bigdata/extend/final_proj/testData/points"), new Path("/home/bigdata/extend/final_proj/testData/clusters"), new Path("/home/bigdata/extend/final_proj/output"), 0.001,10,true,0,false);
        SequenceFile.Reader reader = new SequenceFile.Reader(fs,new Path("/home/bigdata/extend/final_proj/output/clusteredPoints/part-m-00000"), conf);        
        IntWritable key = new IntWritable();   
        WeightedPropertyVectorWritable value = new WeightedPropertyVectorWritable();
        FileWriter fw = new FileWriter("/home/bigdata/extend/final_proj/out.txt");
        while (reader.next(key, value)) {      
        	fw.write(value.toString() + " belongs to cluster " + key.toString()+"\n");    
        }    
        reader.close(); 
        fw.close();
        System.out.println("finished");
	}
	
	public static void writePointsToFile(List<Vector> points,String fileName,FileSystem fs,Configuration conf)throws Exception{
		Path path=new Path(fileName);
		
		SequenceFile.Writer writer=new SequenceFile.Writer(fs, conf, path, LongWritable.class, VectorWritable.class);
		long recNum=0;
		VectorWritable vec=new VectorWritable();
		for(Vector point:points){
			vec.set(point);
			writer.append(new LongWritable(recNum++),vec);
		}
		writer.close();
	}
	public static List<Vector> getPoints(double[][] raw){
		List<Vector> points=new ArrayList<Vector>();
		for(int i=0;i<raw.length;i++){
			double[] fr=raw[i];
			Vector vec=new RandomAccessSparseVector(fr.length);
			vec.assign(fr);
			points.add(vec);
		}
		
		return points;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

