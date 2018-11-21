package com.css.reducejoin;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TableReducer extends Reducer<Text, TableBean, TableBean, NullWritable>{

	@Override
	protected void reduce(Text key, Iterable<TableBean> values,
			Context context) throws IOException, InterruptedException {
		// 创建集合  存放订单数据
		ArrayList<TableBean> orderBean = new ArrayList<TableBean>();
		
		// 商品存储
		TableBean pdBean = new TableBean(); // 把pd商品中商品名  拷贝到orderBean
		
		for (TableBean v : values) {
			if ("0".equals(v.getFlag())) { // 订单表
				// 1.创建一个临时变量  拷贝数据
				TableBean tableBean = new TableBean();
				// 2.拷贝
				try {
					BeanUtils.copyProperties(tableBean, v);
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
				orderBean.add(tableBean);
			}else {
				try {
					BeanUtils.copyProperties(pdBean, v);
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		
		// 拼接表
		for (TableBean tableBean : orderBean) {
			// 加入商品名
			tableBean.setpName(pdBean.getpName());
			context.write(tableBean, NullWritable.get());
		}
	}

}
