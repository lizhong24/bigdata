package com.demo.scala06

import java.io.File

//扫描文件的数据条数
object FileMain {

  //定义隐式转换
  implicit def file2RichFile(file:File) = new RichFile(file)

  def main(args: Array[String]): Unit = {
    //1.加载文件
    val file = new File("e:/weblog.log")

    //2.打印条数
    println(file.count())
  }
}
