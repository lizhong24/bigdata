package com.css.hdfs;

/**
 * 思路：
 * 接口设计
 */
public interface Mapper {
	// 调用方法
	public void map(String line, Context context);
}
