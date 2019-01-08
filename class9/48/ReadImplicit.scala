package com.demo.scala06

import java.io.File

import scala.io.Source

object ReadImplicit {
  //定义隐式类
  implicit class FileRead(file:File){
    //读取文件
    def read = Source.fromFile(file).mkString
  }

  def main(args: Array[String]): Unit = {
    val file = new File("e:/weblog.log")
    println(file.read)
  }
}
