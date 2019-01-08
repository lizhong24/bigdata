package com.demo.scala06;

public class Person1 implements Comparable<Person1>{
    //定义属性
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person1(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person1 o) {
        //升序
        return this.age - o.age;
    }
}
