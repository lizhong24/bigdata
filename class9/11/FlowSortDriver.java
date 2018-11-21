package com.css.flowsort;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class FlowSortDriver {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// 1.获取job信息
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
		
		// 2.获取jar包
		job.setJarByClass(FlowSortDriver.class);
		
		// 3.获取自定义的mapper与reducer类
		job.setMapperClass(FlowSortMapper.class);
		job.setReducerClass(FlowSortReducer.class);
		
		// 4.设置map输出的数据类型
		job.setMapOutputKeyClass(FlowBean.class);
		job.setMapOutputValueClass(Text.class);
		
		// 5.设置reduce输出的数据类型（最终的数据类型）
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FlowBean.class);
		
		//添加自定义分区
		job.setPartitionerClass(FlowSortPartitioner.class);
		job.setNumReduceTasks(5);
		
		// 6.设置输入存在的路径与处理后的结果路径
		FileInputFormat.setInputPaths(job, new Path("c:/flow1024/in"));
		FileOutputFormat.setOutputPath(job, new Path("c:/flow1024/out1"));
		
		// 7.提交任务
		boolean rs = job.waitForCompletion(true);
		System.out.println(rs ? 0 : 1);
	}
}
