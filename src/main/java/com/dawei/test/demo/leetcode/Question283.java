package com.dawei.test.demo.leetcode;

/**
 * 283. 移动零 https://leetcode-cn.com/problems/move-zeroes/
 *
 * @author sinbad on 2020/11/19.
 */
public class Question283 {

	public static void main(String[] args) {
		new Question283().moveZeroes(new int[] { 0, 1 });
	}

	public void moveZeroes(int[] nums) {
		// 双指针
		int index = 0;
		int zeroIndex = 0;
		int length = nums.length;
		if (length <= 1) {
			return;
		}
		while (index < length) {
			if (nums[index] != 0) {
				int temp = nums[zeroIndex];
				nums[zeroIndex] = nums[index];
				nums[index] = temp;
				zeroIndex++;
			}
			index++;
		}
	}
}
