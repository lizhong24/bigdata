package com.demo.pvcount;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

/**
 * 
 * @author 李众 
 * @Date 2018-12-22 20:43:26
 */
public class PvCountSplitBolt implements IRichBolt{

	private OutputCollector collector;
	
	//一个bolt即将关闭时调用 不能保证一定被调用 资源清理
	@Override
	public void cleanup() {
	}

	private int pvnum = 0;
	//业务逻辑 分布式 集群 并发度 线程 （接收tuple然后进行处理）
	@Override
	public void execute(Tuple input) {
		//1.获取数据
		String line = input.getStringByField("logs");
		
		//2.切分数据
		String[] fields = line.split("\t");
		String session_id = fields[1];
		
		//3.局部累加
		if (session_id != null) {
			//累加
			pvnum++;
			//输出
			collector.emit(new Values(Thread.currentThread().getId(),pvnum));
		}
	}

	//初始化调用
	@Override
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector collector) {
		this.collector = collector;
	}

	//声明
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		//声明输出
		declarer.declare(new Fields("threadid","pvnum"));
	}

	//配置
	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

}
