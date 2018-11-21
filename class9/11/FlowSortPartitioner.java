package com.css.flowsort;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class FlowSortPartitioner extends Partitioner<FlowBean, Text>{

	// 根据手机号前三位进行分区
	@Override
	public int getPartition(FlowBean key, Text value, int numPartitions) {
		// 获取手机号前三位
		String phoneNum = value.toString().substring(0, 3);
		// 分区
		int partitioner = 4;
		if ("135".equals(phoneNum)) {
			return 0;
		}else if ("137".equals(phoneNum)) {
			return 1;
		}else if ("138".equals(phoneNum)) {
			return 2;
		}else if ("139".equals(phoneNum)) {
			return 3;
		}
		return partitioner;
	}
}
