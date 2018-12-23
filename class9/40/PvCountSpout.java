package com.demo.pvcount;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

/**
 * 
 * @author 李众 
 * @Date 2018-12-22 20:33:40
 */
public class PvCountSpout implements IRichSpout{

	private SpoutOutputCollector collector;
	private BufferedReader br;
	private String line;
	
	@Override
	public void nextTuple() {
		//发送读取的数据的每一行
		try {
			while((line = br.readLine())!= null) {
				//发送数据到splitbolt
				collector.emit(new Values(line));
				//设置延迟
				Thread.sleep(500);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void open(Map arg0, TopologyContext arg1, SpoutOutputCollector collector) {
		this.collector = collector;
		
		//读取文件
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream("e:/weblog.log")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		//声明
		declarer.declare(new Fields("logs"));
	}
	
	//处理tuple成功 回调的方法
	@Override
	public void ack(Object arg0) {
	}

	//如果spout在失效的模式中 调用此方法来激活
	@Override
	public void activate() {
	}

	//在spout程序关闭前执行 不能保证一定被执行 kill -9 是不执行 storm kill 是不执行
	@Override
	public void close() {
	}

	//在spout失效期间，nextTuple不会被调用
	@Override
	public void deactivate() {
	}

	//处理tuple失败回调的方法
	@Override
	public void fail(Object arg0) {
	}

	//配置
	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

}
