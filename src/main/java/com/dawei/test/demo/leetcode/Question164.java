package com.dawei.test.demo.leetcode;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/maximum-gap/ 164. 最大间距
 *
 * @author sinbad on 2020/11/26.
 */
public class Question164 {

	public static void main(String[] args) {
		System.out.println(new Question164().maximumGap(new int[] {}));
	}

	public int maximumGap(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		Arrays.sort(nums);
		int gap = Integer.MIN_VALUE;
		int lastNum = nums[0];
		for (int i = 1; i < nums.length; i++) {
			gap = Math.max(nums[i] - lastNum, gap);
			lastNum = nums[i];
		}
		return gap;
	}
}
