package com.hbase.mr;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class ReadLoveMapper extends TableMapper<ImmutableBytesWritable, Put> {
    @Override
    protected void map(ImmutableBytesWritable key, Result value, Context context) throws IOException, InterruptedException {
        //1.读取数据 拿到一个rowkey的数据
        Put put = new Put(key.get());

        //2.遍历column
        for (Cell c : value.rawCells()) {
            //3.加入列族数据 当前列族是info要 不是info列族的不要 是info数据才导入lovemr表中
            if ("info".equals(Bytes.toString(CellUtil.cloneFamily(c)))){
                //4.拿到指定列的数据
                if ("name".equals(Bytes.toString(CellUtil.cloneQualifier(c)))){
                    put.add(c);
                }
            }
        }
        context.write(key,put);
    }
}
