package com.demo.wc;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

/**
 * 
 * @author 李众 
 * @Date 2018-12-19 22:31:16
 */

public class WordCountDriver {
	public static void main(String[] args) {
		//1.hadoop->Job storm->topology 创建拓扑
		TopologyBuilder builder = new TopologyBuilder();
		//2.指定设置
		builder.setSpout("WordCountSpout", new WordCountSpout(), 1);
		builder.setBolt("WordCountSplitBolt", new WordCountSplitBolt(), 4).fieldsGrouping("WordCountSpout", new Fields("wordcount"));
		builder.setBolt("WordCountBolt", new WordCountBolt(), 2).fieldsGrouping("WordCountSplitBolt", new Fields("word"));
		
		//3.创建配置信息
		Config conf = new Config();
		
		//4.提交任务
		LocalCluster localCluster = new LocalCluster();
		localCluster.submitTopology("wordcounttopology", conf, builder.createTopology());
	}
}
