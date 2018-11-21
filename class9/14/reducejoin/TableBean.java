package com.css.reducejoin;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class TableBean implements Writable{
	
	// 封装对应字段
	private String order_id; //订单id
	private String pid; // 产品id
	private int amount; // 产品数量
	private String pName; // 产品名称
	private String flag; // 判断是订单表还是商品表
	
	public TableBean() {
		super();
	}
	
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(order_id);
		out.writeUTF(pid);
		out.writeInt(amount);
		out.writeUTF(pName);
		out.writeUTF(flag);
	}
	
	@Override
	public void readFields(DataInput in) throws IOException {
		order_id = in.readUTF();
		pid = in.readUTF();
		amount = in.readInt();
		pName = in.readUTF();
		flag = in.readUTF();
	}

	@Override
	public String toString() {
		return order_id + "\t" + pName + "\t" + amount;
	}
	
	
}
