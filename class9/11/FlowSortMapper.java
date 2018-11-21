package com.css.flowsort;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class FlowSortMapper extends Mapper<LongWritable, Text, FlowBean, Text>{

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		// 1.获取一行数据
		String line = value.toString();
		// 2.切割
		String[] fields = line.split("\t");
		// 3.取出关键字段
		long upFlow = Long.parseLong(fields[1]);
		long dfFlow = Long.parseLong(fields[2]);
		// 4.写出到reducer阶段
		context.write(new FlowBean(upFlow, dfFlow), new Text(fields[0]));
	}

}
