package com.css.order.mr;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class OrderMapper extends Mapper<LongWritable, Text, OrderBean, NullWritable>{

	@Override
	protected void map(LongWritable key, Text value,Context context)
					throws IOException, InterruptedException {
		// 获取每行数据
		String line = value.toString();
		// 切分数据
		String[] fields = line.split("\t");
		// 取出字段
		Integer order_id = Integer.parseInt(fields[0]);
		Double price = Double.parseDouble(fields[2]);
		OrderBean orderBean = new OrderBean(order_id, price);
		// 输出
		context.write(orderBean, NullWritable.get());
	}
}
