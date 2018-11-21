package com.css.flow.partition;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

/**
 * 封装类 数据的传输
 */
public class FlowBean implements Writable{

	// 定义属性
	private long upFlow;
	private long dfFlow;
	private long flowSum;
	
	public FlowBean() {
		
	}
	
	// 流量累加
	public FlowBean(long upFlow, long dfFlow) {
		this.upFlow = upFlow;
		this.dfFlow = dfFlow;
		this.flowSum = upFlow + dfFlow;
	}

	// 反序列化
	@Override
	public void readFields(DataInput in) throws IOException {
		upFlow = in.readLong();
		dfFlow = in.readLong();
		flowSum = in.readLong();
		
	}

	// 序列化
	@Override
	public void write(DataOutput out) throws IOException {
		out.writeLong(upFlow);
		out.writeLong(dfFlow);
		out.writeLong(flowSum);
	}
	
	
	@Override
	public String toString() {
		return upFlow + "\t" + dfFlow + "\t" + flowSum;
	}

	public long getUpFlow() {
		return upFlow;
	}

	public void setUpFlow(long upFlow) {
		this.upFlow = upFlow;
	}

	public long getDfFlow() {
		return dfFlow;
	}

	public void setDfFlow(long dfFlow) {
		this.dfFlow = dfFlow;
	}

	public long getFlowSum() {
		return flowSum;
	}

	public void setFlowSum(long flowSum) {
		this.flowSum = flowSum;
	}

}
