package com.dawei.test.demo.linked;

import com.alibaba.fastjson.JSON;


import java.util.LinkedList;

/**
 * @author Dawei 2019/8/22.
 *
 * 对linkedList 学习
 */
public class LinkedListDemo {


    public static void main(String[] args) {


        LinkedNodeList<Integer> linkedNodeList = new LinkedNodeList<>();

        linkedNodeList.addByFirst(1);
        linkedNodeList.addByFirst(2);
        linkedNodeList.addByFirst(3);

        System.out.println(JSON.toJSONString(linkedNodeList));


//        linkedNodeList = new LinkedNodeList<>();
//        linkedNodeList.addByLast(2);
//
//
        LinkedList<Integer> linkedList = new LinkedList<>();
//
//        linkedList.add(1);
//        linkedList.add(2);
//        linkedList.add(null);
//        linkedList.add(3);
//        linkedList.add(null);
//
//        Integer integer = linkedList.get(0);
//        Integer first = linkedList.getFirst();
//        Integer last = linkedList.getLast();
//        System.out.println("integer  " +  integer);
//        System.out.println("first  " +  first);
//        System.out.println("last  " +  last);



    }
}
