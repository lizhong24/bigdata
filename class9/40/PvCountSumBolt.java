package com.demo.pvcount;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;

/**
 * 
 * @author 李众 
 * @Date 2018-12-22 20:55:27
 */
public class PvCountSumBolt implements IRichBolt{

	private HashMap<Long, Integer> hashMap = new HashMap<>();
	
	@Override
	public void cleanup() {
	}

	//全局累加求和 业务逻辑
	@Override
	public void execute(Tuple input) {
		//1.获取数据
		Long threadid = input.getLongByField("threadid");
		Integer pvnum = input.getIntegerByField("pvnum");
		
		//2.创建集合 存储(threadid,pvnum) 15 20
		hashMap.put(threadid, pvnum);
		
		//3.累加求和(拿到集合中所有value值)
		Iterator<Integer> iterator = hashMap.values().iterator();
		
		//4.清空之前的数据
		int sumnum = 0;
		while (iterator.hasNext()) {
			sumnum += iterator.next();
		}
		
		System.err.println(Thread.currentThread().getName() + "总访问量为->" + sumnum);
	}

	@Override
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector arg2) {
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer arg0) {
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

}
