package com.dawei.test.demo.arrays;

import java.util.*;

/**
 * @author by Dawei on 2018/7/19.
 */
public class ArraysListDemo {


    public static void main(String[] args) {


        List<String> testList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            testList.add("test" + i);
        }

        System.out.println("origin : " + Arrays.toString(testList.toArray()));

        //随机乱序
        Collections.shuffle(testList);
        System.out.println("shuffle no random: " + Arrays.toString(testList.toArray()));

        Collections.shuffle(testList, new Random(System.currentTimeMillis()));
        System.out.println("shuffle with random: " + Arrays.toString(testList.toArray()));


        //排序
        Collections.sort(testList);
        System.out.println("sort: " + Arrays.toString(testList.toArray()));

        //search 数据顺序必须是升序的
        System.out.println("binarySearch where test6:" + Collections.binarySearch(testList, "test6"));

        //倒序
        Collections.reverse(testList);
        System.out.println("sort reverse: " + Arrays.toString(testList.toArray()));


        //fill
        Collections.fill(testList, "Hello");
        System.out.println("fill: " + Arrays.toString(testList.toArray()));

        System.out.println("==========================");
        System.out.println();

        Map<String, String> testMap = new HashMap<>();

        testMap.put("test1", "1");
        System.out.println("Size : " + testMap.size());
        testMap.put(null, "2");
        System.out.println("Size : " + testMap.size());

        System.out.println("Value in map what key is null : " + testMap.get(null));
        testMap.remove(null);
        System.out.println("Size : " + testMap.size());
    }


}
