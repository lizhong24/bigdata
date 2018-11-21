package com.css.hdfs03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * hdfs读写文件
 */
public class ReadData {
	
	FileSystem fs =null;
	@Before
	public  void init() throws IOException, InterruptedException, URISyntaxException {
		// 1.加载配置
		Configuration conf = new Configuration();
		// 2.构造客户端
		fs = FileSystem.get(new URI("hdfs://192.168.146.132:9000/"), conf, "root");
	}
	
	/**
	 * 读数据方式一
	 */
	@Test
	public void testReadData1() throws IllegalArgumentException, IOException{
		// 1.拿到流
		FSDataInputStream in = fs.open(new Path("/a.txt"));
		byte[] buf = new byte[1024];
		in.read(buf);
		System.out.println(new String(buf));
		// 2.关闭资源
		in.close();
		fs.close();
	}
	
	/**
	 * 读数据方式二
	 */
	@Test
	public void testReadData2() throws IllegalArgumentException, IOException{
		// 1.拿到流
		FSDataInputStream in = fs.open(new Path("/hdfs-site.xml"));
		// 2.缓冲流
		BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
		// 3.按行读取
		String line = null;
		// 4.读数据
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		// 5.关闭资源
		br.close();
		in.close();
		fs.close();
	}
	
	/**
	 * 读取hdfs中指定偏移量
	 */
	@Test
	public void testRandomRead() throws IllegalArgumentException, IOException{
		// 1.拿到流
		FSDataInputStream in = fs.open(new Path("/hdfs-site.xml"));
		// 2.移动文件读取指针到指定位置
		in.seek(14);
		byte[] b = new byte[5];
		// 3.从指针位置开始读取数组b的长度个字节
		in.read(b);
		System.out.println(new String(b));
		// 4.关闭资源
		in.close();
	}
	
	/**
	 * 在hdfs中写数据方式一
	 */
	@Test
	public void testWriteData() throws IllegalArgumentException, IOException{
		// 1.输出流
		FSDataOutputStream out = fs.create(new Path("/windows.txt"), false);
		// 2.输入流
		FileInputStream in = new FileInputStream("C:\\Users\\Administrator\\Desktop\\1012.txt");
		byte[] buf = new byte[1024];
		int read = 0;
		while ((read = in.read(buf)) != -1) {
			out.write(buf, 0, read);
		}
		// 3.关闭资源
		in.close();
		out.close();
		fs.close();
	}
	
	/**
	 * 在hdfs中写数据方式二
	 */
	@Test
	public void testWriteData1() throws IllegalArgumentException, IOException{
		// 1.创建输出流
		FSDataOutputStream out = fs.create(new Path("/love"));
		// 2.写数据
		out.write("Areyouokmylove".getBytes());
		// 3.关闭资源
		IOUtils.closeStream(out);
		fs.close();
	}
}
