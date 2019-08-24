package com.dawei.test.demo.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author by Dawei on 2018/7/19.
 */
public class ArraysListDemo {


    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();

        for(int i = 1; i <= 5; i++) {
            list1.add(i);
        }
        List<Integer> list2 = new ArrayList<>();

        for(int i = 6; i <= 10; i++) {
            list2.add(i);
        }
        Collections.reverse(list1);

        List<Integer> list = new ArrayList<>(list1);
        list.addAll(list2);
        System.out.println(Arrays.toString(list.toArray()));

    }


}
