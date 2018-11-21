package com.css.combine;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCountDriver {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// 获取job信息
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
		// 获取jar包
		job.setJarByClass(WordCountDriver.class);
		// 获取自定义的mapper与reducer类
		job.setMapperClass(WordCountMapper.class);
		job.setReducerClass(WordCountReducer.class);
		// 设置map输出的数据类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		// 设置reduce输出的数据类型（最终的数据类型）
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		// 指定运行的inputformat方式  默认的方式是textinputformat（小文件优化）
		job.setInputFormatClass(CombineTextInputFormat.class);
		CombineTextInputFormat.setMaxInputSplitSize(job, 4194304); // 最大4M
		CombineTextInputFormat.setMinInputSplitSize(job, 3145728); // 最小3M
		// 设置输入存在的路径与处理后的结果路径
		FileInputFormat.setInputPaths(job, new Path("c:/in1024/"));
		FileOutputFormat.setOutputPath(job, new Path("c:/out1024/"));
		// 提交任务
		boolean rs = job.waitForCompletion(true);
		System.out.println(rs?0:1);
		
	}
}
