package com.dawei.test.demo.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author by Dawei on 2018/7/16.
 */
public class ForOrIf {


    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();

        for(int i = 0; i < 50; i++ ){
            list.add(i);
        }

        for(int i = 0; i < list.size(); i++) {

            for(int j = i; j < list.size(); j = j + 5) {
                list1.add(list.get(j));
            }
        }

        System.out.println(Arrays.toString(list1.toArray()));


    }
}
