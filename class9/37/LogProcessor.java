package com.css.kafka.kafka_stream;

import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;

/**
 * 数据清洗
 * @author 李众 
 * @Date 2018-12-17 20:18:09
 */
public class LogProcessor implements Processor<byte[], byte[]>{

	private ProcessorContext context;
	
	//初始化
	public void init(ProcessorContext context) {
		//传输
		this.context = context;
	}

	//具体业务逻辑
	public void process(byte[] key, byte[] value) {
		//1.拿到消息数据，转成字符串
		String message = new String(value);
		
		//2.如果包含-  去除
		if (message.contains("-")) {
			//3.把- 去掉 之后去掉左侧数据
			message = message.split("-")[1];
		}
		//4.发送数据
		context.forward(key, message.getBytes());
	}

	//释放资源
	public void close() {
	}
}
