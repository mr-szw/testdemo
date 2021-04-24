package com.dawei.test.demo.leetcode;

/**
 * https://leetcode-cn.com/problems/longest-mountain-in-array/
 *
 * @author sinbad on 2020/08/19.
 */
public class Question845 {


	public static void main(String[] args) {
		System.out.println(new Question845().longestMountain(new int[]{2, 1, 4, 7, 3, 2, 5}));
	}


	public int longestMountain(int[] arr) {
		int start = -1;
		int max = 0;
		int length = arr.length;
		for (int i = 1; i < length; i++) {
			//上升
			if (arr[i-1] < arr[i]) {
				//初始位置或者 上升山脉的起点
				if (i == 1 || arr[i-2] >= arr[i -1]) {
					start = i -1;
				}
			} else if (arr[i-1] > arr[i]) {
				//记录过开始值
				if (start >= 0) {
					max = Math.max(max, i - start + 1);
				}
			} else {
				//平坦时重置
				start = -1;
			}
		}
		return max;
	}

}
