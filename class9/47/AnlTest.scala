package com.demo.scala05

object AnlTest extends Anl {
  override type T = String

  def main(args: Array[String]): Unit = {
    AnlTest.sleep("睡得很香")
  }
}
