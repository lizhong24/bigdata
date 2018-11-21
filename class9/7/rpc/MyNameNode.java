package com.css.rpc.server;

import com.css.rpc.protocol.ClientNamenodeProtocol;

/**
 * 自定义协议的实现类
 */
public class MyNameNode implements ClientNamenodeProtocol{
	@Override
	public String getMetaData(String path) {
		return path + ": 3 - {BLK_1,BLK_2,BLK_3...}";
	}
}
