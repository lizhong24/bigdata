package com.demo.pvcount;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

/**
 * 
 * @author 李众 
 * @Date 2018-12-22 21:07:25
 */

public class PvCountDriver_Shuffle {
	public static void main(String[] args) {
		// 1.hadoop->Job storm->topology 创建拓扑
		TopologyBuilder builder = new TopologyBuilder();

		// 2.指定设置
		builder.setSpout("PvCountSpout", new PvCountSpout(), 1);
		builder.setBolt("PvCountSplitBolt", new PvCountSplitBolt(), 6).setNumTasks(4)
				.shuffleGrouping("PvCountSpout");
		builder.setBolt("PvCountSumBolt", new PvCountSumBolt(), 2).shuffleGrouping("PvCountSplitBolt");

		// 3.创建配置信息
		Config conf = new Config();
		conf.setNumWorkers(2);

		// 4.提交任务
		LocalCluster localCluster = new LocalCluster();
		localCluster.submitTopology("pvcounttopology", conf, builder.createTopology());
	}
}
