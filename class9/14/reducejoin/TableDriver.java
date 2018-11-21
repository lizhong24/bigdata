package com.css.reducejoin;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class TableDriver {
	public static void main(String[] args) throws IOException, URISyntaxException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
		
		job.setJarByClass(TableDriver.class);
		
		job.setMapperClass(TableMapper.class);
		job.setReducerClass(TableReducer.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(TableBean.class);
		
		job.setOutputKeyClass(TableBean.class);
		job.setOutputValueClass(NullWritable.class);
		
		FileInputFormat.setInputPaths(job, new Path("c:/reduce1029/in"));
		FileOutputFormat.setOutputPath(job, new Path("c:/reduce1029/out"));
		
		boolean rs = job.waitForCompletion(true);
		System.out.println(rs ? 0 : 1);
	}
}
