package com.demo.scala02;

public class JavaTest {
    // java中的可变参数
    public static void m1(String ...args){

    }

    public static void main(String[] args) {
        m1("a");
        m1("a", "b", "c");
    }
}
