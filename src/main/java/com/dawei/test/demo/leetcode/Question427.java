package com.dawei.test.demo.leetcode;


import java.util.Arrays;

import com.google.gson.Gson;

/**
 * 494. 目标和
 * https://leetcode-cn.com/problems/target-sum/
 *
 * @author sinbad on 2020/10/12.
 * <p>
 */
public class Question427 {


	public static void main(String[] args) {


		System.out.println(new Gson().toJson(new Question427().canPartition(new int[]{1, 5, 14, 6})));
	}


	public boolean canPartition(int[] nums) {
		//1、两个子集  总和是偶数
		//2、找出一个子集为 1/2 的total 存在多数之和为target dp
		int total = Arrays.stream(nums).parallel().reduce(0, Integer::sum);

		if ((total & 1) == 1) {
			return false;
		}
		Arrays.sort(nums);


		boolean[] canPutData = new boolean[(total / 2) + 1];

		int target = total / 2;
		if (nums[0] <= target) {
			canPutData[nums[0]] = true;
		}
		for (int i = 1; i < nums.length; i++) {

			for (int num = target; nums[i] <= num; num--) {
				if (canPutData[target]) {
					return true;
				}

				canPutData[num] = canPutData[num] || canPutData[num - nums[i]];

			}


		}


		return hasNumLessTarget(nums, total / 2);
	}

	private boolean hasNumLessTarget(int[] nums, int target) {


		int length = nums.length;
		int half = length / 2;
		for (int i = length - 1; i > half; i--) {
			if (nums[i] > target) {
				return false;
			}
			target = target - nums[i];
		}
		return true;
	}


	private boolean hasTargetSum(int[] nums, int startIndex, int target) {

		if (target < 0) {
			return false;
		}
		if (target == 0) {
			return true;
		}

		int length = nums.length;
		if (startIndex >= length) {
			return false;
		}

		int lastNum = -1;
		boolean lastResult = true;
		for (int i = startIndex; i < length; i++) {
			if (!lastResult && lastNum == nums[i]) {
				continue;
			}
			lastResult = hasTargetSum(nums, i + 1, target - nums[i]);
			if (lastResult) {
				return true;
			} else {
				if (lastNum != nums[i]) {
					lastNum = nums[i];
				}
			}

		}
		return false;
	}


}

