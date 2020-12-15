package com.dawei.test.demo.leetcode;

import com.google.gson.Gson;

/**
 * 321. 拼接最大数
 * https://leetcode-cn.com/problems/create-maximum-number/
 *
 * @author sinbad on 2020/12/14.
 */
public class Question321 {


	public static void main(String[] args) {

		/**
		 * [7,6,1,9,3,2,3,1,1]
		 * [4,0,9,9,0,5,5,4,7]
		 * 9
		 */
		int[] nums1 = {7,6,1,9,3,2,3,1,1};
		int[] nums2 = {4,0,9,9,0,5,5,4,7};
		System.out.println(new Gson().toJson(new Question321().maxNumber(nums1, nums2, 9)));
	}


	//做单调栈并合并
	public int[] maxNumber(int[] nums1, int[] nums2, int k) {

		int[] result = null;

		int needNumFor1Min = Math.max(0, k - nums2.length);
		int needNumFor1Max = Math.min(nums1.length, k);


		for (int i = needNumFor1Min; i <= needNumFor1Max; i++) {
			int[] needNums1 = getHelpNum(nums1, i);
			int[] needNums2 = getHelpNum(nums2, k - i);

			int[] mergeNums = mergeNums(needNums1, needNums2);
			result = compare(mergeNums, result);
		}
		return result;
	}


	//单调栈获取数
	private int[] getHelpNum(int[] nums, int needNum) {
		if (needNum == 0) {
			return null;
		}
		int length = nums.length;
		int deleteNum = length > needNum ? length - needNum : 0;
		//全部需要
		if (deleteNum == 0) {
			return nums;
		}
		int index = 0;
		int[] result = new int[needNum];
		result[index] = nums[0];
		for (int i = 1; i < nums.length; i++) {
			while (index >= 0 && deleteNum > 0 && result[index] < nums[i] && length - i >= deleteNum) {
				index--;
				deleteNum--;
			}
			if (index < needNum - 1) {
				index++;
				result[index] = nums[i];
			}
		}
		return result;
	}

	//合并两个数字
	private int[] mergeNums(int[] nums1, int[] nums2) {
		if (nums1 == null) {
			return nums2;
		}
		if (nums2 == null) {
			return nums1;
		}
		int[] result = new int[nums1.length + nums2.length];
		int num = 0;
		int num1Length = nums1.length;
		int num2Length = nums2.length;
		int num1Index = 0;
		int num2Index = 0;
		while (num2Index < num2Length && num1Index < num1Length) {
			if (nums1[num1Index] < nums2[num2Index]) {
				result[num++] = (nums2[num2Index++]);
			} else {
				result[num++] = (nums1[num1Index++]);
			}
		}
		while (num1Index < num1Length) {
			result[num++] = nums1[num1Index++];
		}
		while (num2Index < num2Length) {
			result[num++] = nums2[num2Index++];
		}

		return result;
	}

	//比较两个数组
	private int[] compare(int[] nums1, int[] nums2) {
		if (nums1 == null) {
			return nums2;
		}
		if (nums2 == null) {
			return nums1;
		}
		int length = nums1.length;
		int index = 0;
		while (index < length) {
			if (nums1[index] < nums2[index]) {
				return nums2;
			} else if (nums1[index] > nums2[index]) {
				return nums1;
			}
			index++;
		}
		return nums2;
	}

}
