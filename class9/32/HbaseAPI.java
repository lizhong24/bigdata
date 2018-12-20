package com.demo.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HbaseAPI {

    //配置信息
    public static Configuration conf;

    //获取配置信息
    static {
        //alt + enter
        conf = HBaseConfiguration.create();
    }

    //1.判断一张表是否存在
    public static boolean isExist(String tableName) throws IOException {
        //对表操作需要使用HBaseAdmin
        Connection connection = ConnectionFactory.createConnection(conf);
        //管理表
        HBaseAdmin admin = (HBaseAdmin) connection.getAdmin();

        return admin.tableExists(TableName.valueOf(tableName));
    }

    //2.在HBase集群创建表  create 'user','info','info1'
    public static void createTable(String tableName,String... columnFamily) throws IOException {
        //对表操作需要用HBaseAdmin
        Connection connection = ConnectionFactory.createConnection(conf);
        //管理表
        HBaseAdmin admin = (HBaseAdmin) connection.getAdmin();

        //1.表如果存在  请输入其他表名
        if (isExist(tableName)){
            System.out.println("表已经存在，请输入其它表名");
        }else{
            //2.注意，创建表的话 需要创建一个描述器
            HTableDescriptor htd = new HTableDescriptor(TableName.valueOf(tableName));

            //3.创建列族
            for (String cf : columnFamily) {
                htd.addFamily(new HColumnDescriptor(cf));
            }

            //4.创建表
            admin.createTable(htd);
            System.out.println("表已创建成功！");
        }
    }

    //3.删除HBase中的表
    public static void deleteTable(String tableName) throws IOException{
        //对表操作需要使用HBaseAdmin
        Connection connection = ConnectionFactory.createConnection(conf);
        //管理表
        HBaseAdmin admin = (HBaseAdmin) connection.getAdmin();

        //1.如果表存在 删除 否则打印不存在
        //需要先指定表不可用 再删除
        if (isExist(tableName)){
            //2.指定不可用
            admin.disableTable(TableName.valueOf(tableName));
            admin.deleteTable(TableName.valueOf(tableName));
        }else {
            System.out.println("表不存在，请重新输入表名！");
        }
    }

    //4.添加数据put 'user','rowKey'
    public static void addRow(String tableName, String rowkey, String cf, String column, String value) throws IOException {
        //对表操作需要使用HBaseAdmin
        Connection connection = ConnectionFactory.createConnection(conf);
        //拿到表对象
        Table t = connection.getTable(TableName.valueOf(tableName));
        HBaseAdmin admin = (HBaseAdmin) connection.getAdmin();

        //1.用put方式加入数据
        Put p = new Put(Bytes.toBytes(rowkey));
        //2.加入数据
        p.addColumn(Bytes.toBytes(cf),Bytes.toBytes(column),Bytes.toBytes(value));
        t.put(p);
    }

    //5.删除表中一行数据
    public static void deleteRow(String tableName, String rowkey, String cf) throws IOException {
        //对表操作需要用HBaseAdmin
        Connection connection = ConnectionFactory.createConnection(conf);
        //拿到表对象
        Table t = connection.getTable(TableName.valueOf(tableName));

        //1.根据rowkey删除数据
        Delete d = new Delete(Bytes.toBytes(rowkey));
        //2.删除
        t.delete(d);
    }

    //6.删除多行数据
    public static void deleteAll(String tableName, String... rowkeys) throws IOException {
        //对表操作需要用HBaseAdmin
        Connection connection = ConnectionFactory.createConnection(conf);
        //拿到表对象
        Table t = connection.getTable(TableName.valueOf(tableName));

        //1.把delete封装到集合
        List<Delete> list = new ArrayList<Delete>();
        //2.遍历
        for (String row : rowkeys) {
            Delete d = new Delete(Bytes.toBytes(row));
            list.add(d);
        }
        t.delete(list);
    }

    //7.扫描表数据 scan全表扫描
    public static void scanAll(String tableName) throws IOException {
        //对表操作需要用HBaseAdmin
        Connection connection = ConnectionFactory.createConnection(conf);
        //拿到表对象
        Table t = connection.getTable(TableName.valueOf(tableName));

        //1.实例scan
        Scan s = new Scan();
        //2.拿到Scanner对象
        ResultScanner rs = t.getScanner(s);

        //3.遍历
        for (Result r : rs) {
            Cell[] cells = r.rawCells();
            //遍历具体数据
            for (Cell c : cells) {
                System.out.println("行键为：" + Bytes.toString(CellUtil.cloneRow(c)));
                System.out.println("列族为：" + Bytes.toString(CellUtil.cloneFamily(c)));
                System.out.println("值为：" + Bytes.toString(CellUtil.cloneValue(c)));
            }
        }
    }

    //8.扫描指定的数据
    public static void getRow(String tableName, String rowkey) throws IOException {
        //对表操作需要用HBaseAdmin
        Connection connection = ConnectionFactory.createConnection(conf);
        //拿到表对象
        Table t = connection.getTable(TableName.valueOf(tableName));

        //1.扫描指定数据需要实例Get
        Get g = new Get(Bytes.toBytes(rowkey));
        //2.可加过滤条件
        g.addFamily(Bytes.toBytes("info"));

        Result rs = t.get(g);
        Cell[] cells = rs.rawCells();

        //3.遍历
        //遍历具体数据
        for (Cell c : cells) {
            System.out.println("行键为：" + Bytes.toString(CellUtil.cloneRow(c)));
            System.out.println("列族为：" + Bytes.toString(CellUtil.cloneFamily(c)));
            System.out.println("值为：" + Bytes.toString(CellUtil.cloneValue(c)));
        }
    }

    public static void main(String[] args) throws IOException {
//        System.out.println(isExist("emp11"));
//        createTable("hunter","henshuai","feichangshuai");
//        createTable("hunter","info");

//        deleteTable("hunter");
//        createTable("yangmi","info");
//        addRow("yangmi","101","info","age","18");

//        deleteRow("yangmi","101","info");
//        deleteAll("emp","1001","1002r");
//        scanAll("yangmi");
        getRow("lisi","102");
    }
}
