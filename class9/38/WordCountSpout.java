package com.demo.wc;

import java.util.Map;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

/**
 * 需求：单词计数 hello world hello Beijing China
 * 
 * 实现接口：IRichSpout		IRichBolt
 * 继承抽象类：BaseRichSpout		BaseRichBolt	常用
 * 
 * @author 李众 
 * @Date 2018-12-19 21:54:31
 */
public class WordCountSpout extends BaseRichSpout {

	//定义收集器
	private SpoutOutputCollector collector;
	
	//发送数据
	@Override
	public void nextTuple() {
		//1.发送数据 到bolt
		collector.emit(new Values("i like China very much"));
		
		//2.设置延迟
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//创建收集器
	@Override
	public void open(Map arg0, TopologyContext arg1, SpoutOutputCollector collector) {
		this.collector = collector;
	}

	//声明描述
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		//起别名
		declarer.declare(new Fields("itstar"));
	}

}
