package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * 75.颜色分类
 * https://leetcode-cn.com/problems/sort-colors/
 *
 * @author sinbad on 2020/10/07.
 */
public class Question75 {


	public static void main(String[] args) {

		new Question75().sortColors(new int[]{1, 2, 0});
	}

//	public void sortColors(int[] nums) {
//		//倒着找 根据排序交换位置然后交换
//		for (int i = nums.length - 1; i > 0; i--) {
//			for (int j = i -1; j >= 0; j--) {
//				if (nums[i] < nums[j]) {
//					//交换
//					nums[i] = nums[i] ^ nums[j];
//					nums[j] = nums[i] ^ nums[j];
//					nums[i] = nums[i] ^ nums[j];
//				}
//			}
//		}
//	}


	public void sortColors(int[] nums) {
		int left = 0;
		int right = nums.length - 1;
		int index = 0;
		while (left <= right && index <= right) {
			int temp = nums[index];
			if (temp == 0) {
				nums[index] = nums[left];
				nums[left] = temp;
				left++;
			} else if (temp == 2) {
				nums[index] =   nums[right];
				nums[right] = temp;
				right--;
				//换过来的不知道大小 再算一遍
				index--;
			}
			index++;
		}
	}
}
