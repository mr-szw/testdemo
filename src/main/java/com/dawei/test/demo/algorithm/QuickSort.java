package com.dawei.test.demo.algorithm;


import java.util.Arrays;

/**
 * @author by Dawei on 2019/8/16. 快速排序
 */
public class QuickSort {


    private void quickMethod(int[] arrayList, int leftIndex, int rightIndex) {
        int indexTagAndSort = getIndexTagAndSort(arrayList, leftIndex, rightIndex);
        //会存在相等的情况 不判断会持续递归下去出现，造成栈溢出
        if (leftIndex < indexTagAndSort - 1) {
            quickMethod(arrayList, leftIndex, indexTagAndSort -1);
        }
        if (indexTagAndSort + 1 < rightIndex) {
            quickMethod(arrayList, indexTagAndSort + 1, rightIndex);
        }
    }

    private int getIndexTagAndSort(int[] arrayList, int leftIndex, int rightIndex) {
        //取一个标志位
        int tempValue = arrayList[leftIndex];
        //保证不出界
        while (leftIndex < rightIndex) {
            //保证不出界 且右侧的数据都比标志位大
            while (leftIndex < rightIndex && tempValue <= arrayList[rightIndex]) {
                rightIndex--;
            }
            //将比标志位小的值放在标志位上
            arrayList[leftIndex] = arrayList[rightIndex];
            //保证不出界 且左侧的数据都比标志位小
            while (leftIndex < rightIndex && arrayList[leftIndex] <= tempValue) {
                leftIndex++;
            }
            //将比标志位大的数据放在刚才比标志位小的地方 此处实现了 将左边第一个比标志位高的与右边第一个比标志位低的做了交换
            arrayList[rightIndex] = arrayList[leftIndex];
        }
        //跳出循环一定是 左右位置标识相同了 此时由于左右两侧的值都被替换掉，只剩中间位置的值时被取出的标志位了 但是中间位置还是之前或做判断的值，此时需要替换回标志位
        arrayList[leftIndex] = tempValue;
        //此时左右位置标志相同且左右各符合标准 因此可以拆开两侧单独处理了
        return leftIndex;
    }

    public static void main(String[] args) {
        // 初始化一个序列
        int[] array = {
                15, 28, 13, 8, 69, 35
        };

        QuickSort quickSort = new QuickSort();

        System.out.print("排序前:\t");
        System.out.println(Arrays.toString(array));
        quickSort.quickMethod(array, 0, array.length -1);
        System.out.print("排序后:\t");
        System.out.println(Arrays.toString(array));
    }




}
