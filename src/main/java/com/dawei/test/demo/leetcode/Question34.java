package com.dawei.test.demo.leetcode;

import java.util.Arrays;

import com.google.gson.Gson;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * 
 * @author sinbad on 2020/12/01.
 */
public class Question34 {

	public static void main(String[] args) {

		int[] position = new int[] { 1, 1, 2 };
		System.out.println(new Gson().toJson(new Question34().searchRange(position, 1)));
	}

	public int[] searchRange(int[] nums, int target) {
		int[] result = new int[2];
		if (nums.length == 0) {
			Arrays.fill(result, -1);
			return result;
		}
		int index = binarySearch(nums, 0, nums.length - 1, target);

		Arrays.fill(result, index);
		int temp = index - 1;
		while (temp >= 0) {
			if (nums[temp] == target) {
				result[0] = temp;
			} else {
				break;
			}
			temp--;
		}

		temp = index + 1;
		while (temp < nums.length) {
			if (nums[temp] == target) {
				result[1] = temp;
			} else {
				break;
			}
			temp++;
		}
		return result;
	}

	public int binarySearch(int[] nums, int left, int right, int target) {
		while (left <= right) {
			int half = (left + right) / 2;
			int num = nums[half];
			if (num == target) {
				return half;
			} else {
				if (num > target) {
					return binarySearch(nums, left, half - 1, target);
				} else {
					return binarySearch(nums, half + 1, right, target);
				}
			}
		}
		return -1;
	}
}
