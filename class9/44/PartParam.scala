package com.demo.scala02

import java.util.Date

object PartParam extends App {
  def log(date: Date,message: String): Unit = {
    //参数打印
    println(s"$date,$message")
  }
  val date = new Date()
  val logMessage = log(date,_:String)

  log(date, "hello world")
  logMessage("2018马上要过去了！")
}
