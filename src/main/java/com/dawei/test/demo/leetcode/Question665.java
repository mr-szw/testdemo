package com.dawei.test.demo.leetcode;

import java.util.Random;

/**
 * https://leetcode-cn.com/problems/non-decreasing-array/
 * 
 * 665. 非递减数列
 */
public class Question665 {

	public static void main(String[] args) {

		System.out.println(new Question665().checkPossibility(new int[] { 3, 4, 2, 3 }));
		System.out.println(new Question665().checkPossibility2(new int[] { 3, 4, 2, 3 }));
		System.out.println(new Question665().checkPossibility2(new int[] { 1, 4, 2, 3 }));
		System.out.println(new Question665().checkPossibility2(new int[] { 5, 7, 1, 8 }));
	}

	public boolean checkPossibility(int[] nums) {
		int length = nums.length;
		int cnt = 0;
		int lastNum = nums[0];
		for (int i = 1; i < length - 1; ++i) {
			// 递减了
			if (lastNum > nums[i]) {
				cnt++;
				if (cnt > 1) {
					return false;
				}

				if (nums[i -1] > nums[i + 1]) {
					nums[i - 1] = nums[i];
				}
			}
			lastNum = nums[i];
		}
		return true;
	}

	public boolean checkPossibility2(int[] nums) {
		int n = nums.length, cnt = 0;
		for (int i = 0; i < n - 1; ++i) {
			int x = nums[i], y = nums[i + 1];
			if (x > y) {
				cnt++;
				if (cnt > 1) {
					return false;
				}
				if (i > 0 && y < nums[i - 1]) {
					nums[i + 1] = x;
				}
			}
		}
		return true;
	}

}
