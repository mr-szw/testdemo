package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 57. 插入区间
 * https://leetcode-cn.com/problems/insert-interval/
 *
 * @author sinbad on 2020/09/18.
 * 技巧好多 不会玩
 * <p>
 */
public class Question376 {


	public static void main(String[] args) {

		System.out.println(new Question376().wiggleMaxLength(new int[]{1, 1, 7, 4, 9, 2, 5}));
	}

//	public int wiggleMaxLength(int[] nums) {
//
//		if (nums.length < 2) {
//			return nums.length;
//		}
//		int up = 1;
//		int down = 1;
//
//		for (int i = 0; i < nums.length - 1; i++) {
//			if (nums[i] < nums[i + 1]) {
//				up = down + 1;
//			}
//			if (nums[i] > nums[i + 1]) {
//				down = up + 1;
//			}
//		}
//		return Math.max(up, down);
//	}

	public int wiggleMaxLength(int[] nums) {

		if (nums.length < 2) {
			return nums.length;
		}

		boolean flag = nums[1] > nums[0];
		int count = nums[1] == nums[0] ? 1 : 2;

		for (int i = 1; i < nums.length - 1; i++) {
			if (flag != nums[i] >= nums[i - 1]) {
				count++;
				flag = !(nums[i] >= nums[i - 1]);
			}
		}
		return count;
	}
}