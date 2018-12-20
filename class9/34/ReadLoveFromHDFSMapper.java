package com.hbase.mr2;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class ReadLoveFromHDFSMapper extends Mapper<LongWritable, Text, ImmutableBytesWritable, Put> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //1.读取数据
        String line = value.toString();

        //2.切分数据
        String[] fields = line.split("\t");

        //3.封装数据
        byte[] rowkey = Bytes.toBytes(fields[0]);
        byte[] name = Bytes.toBytes(fields[1]);
        byte[] desc = Bytes.toBytes(fields[2]);
        //封装put对象
        Put put = new Put(rowkey);
        put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("name"),name);
        put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("desc"),desc);

        //4.输出到reducer端
        context.write(new ImmutableBytesWritable(rowkey),put);
    }
}
