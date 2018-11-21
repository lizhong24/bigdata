package com.css.hdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;

/**
 * 需求：文件(hello world hello teacher hello john tom ) 统计每个单词出现的次数？
 * 	 数据存储在hdfs、统计出来的结果存储到hdfs
 * 
 * 2004google:dfs/bigtable/mapreduce
 *
 * 大数据解决的问题？
 * 	1.海量数据的存储
 * 		hdfs
 *  2.海量数据的计算
 *  	mapreduce
 *  
 *  思路？
 *    hello 1
 *    world 1
 *    hello 1
 *    ...
 *    
 *  基于用户体验：
 *   用户输入数据
 *   用户处理的方式
 *   用户指定结果数据存储位置
 */
public class HdfsWordCount {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, InterruptedException, URISyntaxException {
		// 反射
		Properties pro = new Properties();
		// 加载配置文件
		pro.load(HdfsWordCount.class.getClassLoader().getResourceAsStream("job.properties"));
		Path inPath = new Path(pro.getProperty("IN_PATH"));
		Path outPath = new Path(pro.getProperty("OUT_PATH"));
		Class<?> mapper_class = Class.forName(pro.getProperty("MAPPER_CLASS"));
		// 实例化
		Mapper mapper = (Mapper) mapper_class.newInstance();
		Context context = new Context();
		// 构建hdfs客户端对象
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(new URI("hdfs://192.168.146.132:9000/"), conf, "root");
		// 读取用户输入的文件
		RemoteIterator<LocatedFileStatus> iter = fs.listFiles(inPath, false);
		while (iter.hasNext()) {
			LocatedFileStatus file = iter.next();
			// 打开路径 获取输入流
			FSDataInputStream in = fs.open(file.getPath());
			BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				// 调用map方法执行业务逻辑
				mapper.map(line, context);
			}
			// 关闭资源
			br.close();
			in.close();
		}
		
		// 如果用户输入的结果路径不存在 则创建一个
		Path out = new Path("/wc/out/");
		if (!fs.exists(out)) {
			fs.mkdirs(out);
		}
		
		// 将缓存的结果放入hdfs中存储
		HashMap<Object, Object> contextMap = context.getContextMap();
		FSDataOutputStream out1 = fs.create(outPath);
		
		// 遍历hashmap
		Set<Entry<Object, Object>> entrySet = contextMap.entrySet();
		for (Entry<Object, Object> entry : entrySet) {
			// 写数据
			out1.write((entry.getKey().toString() + "\t" + entry.getValue() + "\n").getBytes());
		}
		// 关闭资源
		out1.close();
		fs.close();
		
		System.out.println("数据统计结果完成......");
	}
}
