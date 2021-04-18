package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 15.三数之和 https://leetcode-cn.com/problems/3sum/
 * 
 * @author sinbad on 2020/11/12.
 */
public class Question15 {

	public static void main(String[] args) {
		System.out.println(new Question15().threeSum(new int[] { 1, 3, 43, 5, 3 }));
	}

	public List<List<Integer>> threeSum(int[] nums) {
		if (nums.length < 3) {
			return Collections.emptyList();
		}
		List<List<Integer>> resultList = new ArrayList<>();
		// 排序
		Arrays.sort(nums);

		int length = nums.length;

		for (int i = 0; i < length; i++) {
			if (nums[i] > 0) {
				return resultList;
			}
			// 相同推一下
			if (i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}
			int left = i + 1;
			int right = length - 1;
			while (left < right) {
				int temp = nums[i] + nums[left] + nums[right];
				if (temp == 0) {
					resultList.add(Arrays.asList(nums[i], nums[left], nums[right]));
					// 剪枝剔重复
					while (left < right && nums[left] == nums[left + 1]) {
						left++;
					}
					while (right > left && nums[right] == nums[right - 1]) {
						right--;
					}
					left++;
					right--;
				} else if (temp < 0) {
					left++;
				} else if (temp > 0) {
					right--;
				}
			}
		}
		return resultList;
	}

}
