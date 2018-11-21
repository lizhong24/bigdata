package com.css.compress;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;

public class TestCompress {
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		compress("c:/compress1031/intest/test.txt","org.apache.hadoop.io.compress.DefaultCodec");
		compress("c:/compress1031/intest/test.txt","org.apache.hadoop.io.compress.BZip2Codec");
		compress("c:/compress1031/intest/test.txt","org.apache.hadoop.io.compress.GzipCodec");
	}
	
	// 测试压缩方法
	private static void compress(String fileName, String method) throws ClassNotFoundException, IOException{
		// 1.获取输入流
		FileInputStream fis = new FileInputStream(new File(fileName));
		Class<?> cName = Class.forName(method);
		CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(cName, new Configuration());
		// 2.输出流
		FileOutputStream fos = new FileOutputStream(new File(fileName + codec.getDefaultExtension()));
		// 3.创建压缩输出流
		CompressionOutputStream cos = codec.createOutputStream(fos);
		// 4.流的对拷
		IOUtils.copyBytes(fis, cos, 1024*1024*2, false);
		// 5.关闭资源
        fis.close();
        cos.close();
        fos.close();
	}
}
