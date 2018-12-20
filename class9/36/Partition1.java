package com.css.kafka.kafka_producer;

import java.util.Map;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

/**
 * 设置自定义分区
 * @author 李众 
 * @Date 2018-12-15 21:40:01
 */
public class Partition1 implements Partitioner{

	//设置
	public void configure(Map<String, ?> configs) {
	}

	//分区逻辑
	public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
		return 1;
	}

	//释放资源
	public void close() {
	}

}
