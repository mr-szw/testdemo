package com.dawei.test.demo.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

/**
 * @author by Dawei on 2019/2/18. 冒泡排序
 */
public class BubbleSort {


    /**
     * 冒泡排序
     *
     * @param array 排序数组
     */
    private void bubble(int[] array) {
        int temp;
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length - i; j++) {
                if (array[j - 1] > array[j]) {
                    temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
    }

    @Test
    public void test() {
        // 初始化一个序列
        int[] array = {
                1, 3, 4, 5, 2, 6, 9, 7, 8, 0
        };

        System.out.print("排序前:\t");
        System.out.println(Arrays.toString(array));
        bubble(array);
        System.out.print("排序后:\t");
        System.out.println(Arrays.toString(array));
    }


    public List<Integer> addToArrayForm(int[] A, int K) {
        String other = String.valueOf(K);
        int otherLength = other.length();
        int length = A.length;

        List<Integer> resultList = new ArrayList<>();


        int otherIndex = otherLength - 1;
        int numIndex = length - 1;

        int next = 0;
        while(otherIndex >= 0 && numIndex >=0) {
            int result = A[numIndex--] + (other.charAt(otherIndex--) - '0') + next;
            next = result / 10;
            result = result -  next * 10;
            resultList.add(result);


        }
        while(numIndex >=0) {
            resultList.add(A[numIndex--]);
        }
        while(otherIndex >=0) {
            resultList.add((other.charAt(numIndex--) - '0'));
        }
        Collections.reverse(resultList);

        return resultList;
    }

}
