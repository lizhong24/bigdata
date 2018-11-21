package com.css.combine;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable>{

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Context context) throws IOException, InterruptedException {
		// 统计单词出现的次数
		int sum = 0;
		// 累加求和
		for (IntWritable count : values) {
			// 拿到值累加
			sum += count.get();
		}
		// 结果输出
		context.write(key, new IntWritable(sum));
	}

}
