package com.demo.wc;

import java.util.HashMap;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

/**
 * 
 * @author 李众 
 * @Date 2018-12-19 22:18:14
 */
public class WordCountBolt extends BaseRichBolt{

	private Map<String, Integer> map = new HashMap<>();
	
	//累加求和
	@Override
	public void execute(Tuple in) {
		//1.获取数据
		String word = in.getStringByField("word");
		Integer sum = in.getIntegerByField("sum");
		
		//2.业务处理
		if (map.containsKey(word)) {
			//之前出现几次
			Integer count = map.get(word);
			//已有的
			map.put(word, count + sum);
		} else {
			map.put(word, sum);
		}
		
		//3.打印控制台
		System.out.println(Thread.currentThread().getName() + "\t 单词为：" + word + "\t 当前已出现次数为：" + map.get(word));
	}

	@Override
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector arg2) {
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		
	}

}
