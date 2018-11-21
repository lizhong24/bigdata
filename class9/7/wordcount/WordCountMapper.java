package com.css.hdfs;

/**
 * 思路：
 * 添加一个map方法 单词切分 相同key的value ++
 */
public class WordCountMapper implements Mapper{

	@Override
	public void map(String line, Context context) {
		// 拿到这行数据 切分
		String[] words = line.split(" ");
		// 拿到单词 相同的key value++  hello 1 world 1
		for (String word : words) {
			Object value = context.get(word);
			if (null == value) {
				context.write(word, 1);
			}else {
				// 不为空
				int v = (int)value;
				context.write(word, v+1);
			}
		}
	}
}
