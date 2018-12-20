package com.demo.wc;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

/**
 * 
 * @author 李众 
 * @Date 2018-12-19 22:07:10
 */
public class WordCountSplitBolt extends BaseRichBolt {

	//数据继续发送到下一个bolt
	private OutputCollector collector;
	
	//业务逻辑
	@Override
	public void execute(Tuple in) {
		//1.获取数据
		String line = in.getStringByField("itstar");
		
		//2.切分数据
		String[] fields = line.split(" ");
		
		//3.<单词,1> 发送出去 下一个bolt(累加求和)
		for (String w : fields) {
			collector.emit(new Values(w, 1));
		}
	}

	//初始化
	@Override
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector collector) {
		this.collector = collector;
	}

	//声明描述
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("word", "sum"));
	}

}
