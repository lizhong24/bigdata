package com.css.rpc.client;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import com.css.rpc.protocol.ClientNamenodeProtocol;

/**
 * 获取元数据信息
 */
public class GetHDFS {
	public static void main(String[] args) throws IOException {
		// 1.拿到协议
		ClientNamenodeProtocol proxy = RPC.getProxy(ClientNamenodeProtocol.class, 1L, new InetSocketAddress("localhost", 7777), new Configuration());
		// 2.发送请求
		String metaData = proxy.getMetaData("/hello");
		// 3.拿到元数据信息
		System.out.println(metaData);
	}

}
