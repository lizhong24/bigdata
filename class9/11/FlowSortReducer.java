package com.css.flowsort;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class FlowSortReducer extends Reducer<FlowBean, Text, Text, FlowBean>{

	@Override
	protected void reduce(FlowBean key, Iterable<Text> value, Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		context.write(value.iterator().next(), key);
	}

}
