package com.dawei.test.demo.leetcode;

/**
 * https://leetcode-cn.com/problems/find-pivot-index/ 724. 寻找数组的中心索引
 * 
 * @author sinbad on 2021/1/28.
 */
public class Question724 {

	public int pivotIndex(int[] nums) {

		int leftCount = 0;
		int rightCount = 0;

		for (int num : nums) {
			rightCount += num;
		}
		for (int i = 0; i < nums.length; i++) {
			leftCount += nums[i];
			if (leftCount == rightCount) {
				return i;
			}
			rightCount -= nums[i];
		}
		return -1;
	}

}
