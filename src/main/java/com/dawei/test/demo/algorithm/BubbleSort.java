package com.dawei.test.demo.algorithm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

/**
 * @author by Dawei on 2019/2/18. 冒泡排序
 */
public class BubbleSort {


    /**
     * 冒泡排序
     * @param array 排序数组
     */
    private void bubble(int[] array) {
        int temp;
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length -i; j++) {
                if (array[j-1] > array[j]) {
                    temp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = temp;
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

}
