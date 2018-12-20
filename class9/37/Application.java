package com.css.kafka.kafka_stream;

import java.util.Properties;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorSupplier;

/**
 * 需求：对数据进行清洗操作
 * 
 * 思路：hunter-henshuai  把-清洗掉
 * @author 李众 
 * @Date 2018-12-17 20:16:05
 */
public class Application {

	public static void main(String[] args) {
		//1.定义主题 发送到 另外一个主题中 数据清洗
		String oneTopic = "t1";
		String twoTopic = "t2";
		
		//2.设置属性
		Properties prop = new Properties();
		prop.put(StreamsConfig.APPLICATION_ID_CONFIG, "logProcessor");
		prop.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.146.132:9092,192.168.146.133:9092,192.168.146.134:9092");
		
		//3.实例对象
		StreamsConfig config = new StreamsConfig(prop);
		
		//4.流计算 拓扑
		Topology builder = new Topology();
		
		//5.定义kafka组件数据源
		builder.addSource("Source", oneTopic).addProcessor("Processor", new ProcessorSupplier<byte[], byte[]>() {

			public Processor<byte[], byte[]> get() {
				return new LogProcessor();
			}
			//从哪里来
		}, "Source")
		//到哪里去
		.addSink("Sink", twoTopic, "Processor");

		//6.实例化kafkaStream
		KafkaStreams kafkaStreams = new KafkaStreams(builder, prop);
		kafkaStreams.start();
	}
}
