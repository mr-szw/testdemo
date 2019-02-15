package com.dawei.test.demo.algorithm;

import com.sun.istack.internal.NotNull;
import java.util.Arrays;

/**
 * @author by Dawei on 2019/2/15.
 * 堆排序
 * 堆的方式分为最小堆和最大堆
 *        堆顶最小或堆顶最大
 *
 */
public class HeapSort {


    /**
     * 将数据转为堆排列
     * 因为堆形成是个递归过程 过程中父节点[堆顶再循环变化]
     * @param array 整理的数组
     * @param parent 父节点
     */
    private static void createHeap(int parent, @NotNull int[] array, int length) {

        //父节点的值
        int temp = array[parent];
        //左孩子的位置
        int child = parent * 2 + 1;
        while (child < length) {
            //判断左孩子是否大于父节点
            //左孩子小于父节点且有右孩子 获取右孩子位置
            if (array[child] < temp && child + 1 < length) {
                child++;
            }

            //左孩子或右孩子 大于 父节点
            if(array[child] > temp) {
                array[parent] = array[child];
            } else {
                break;
            }
            parent = child;
            child = 2 * child + 1;
        }
        array[parent] = temp;
    }

    private static void heapSort(int[] array) {
        //循环初始化堆
        for (int i = array.length / 2; i>= 0; i--){
            createHeap(i, array, array.length);
        }

        // 进行n-1次循环，完成排序
        for (int i = array.length - 1; i > 0; i--) {
            // 最后一个元素和第一元素进行交换
            int temp = array[i];
            array[i] = array[0];
            array[0] = temp;

            // 筛选 R[0] 结点，得到i-1个结点的堆
            createHeap(0, array, i);
            System.out.format("第 %d 趟: \t", array.length - i);
            System.out.println(Arrays.toString(array));
        }

    }


    public static void main(String[] args) {
        // 初始化一个序列
        int[] array = {
            1, 3, 4, 5, 2, 6, 9, 7, 8, 0
        };


        System.out.print("排序前:\t");
        System.out.println(Arrays.toString(array));
        heapSort(array);
        System.out.print("排序后:\t");
        System.out.println(Arrays.toString(array));
    }















}
