package com.demo.scala06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComTest{
    public static void main(String[] args) {
        Person1 p1 = new Person1("Tom",18);
        Person1 p2 = new Person1("Mary",16);

        List<Person1> person = new ArrayList<Person1>();
        person.add(p1);
        person.add(p2);

        Collections.sort(person);
        for (Person1 p : person) {
            System.out.println("名字为：" + p.getName());
        }
    }
}
