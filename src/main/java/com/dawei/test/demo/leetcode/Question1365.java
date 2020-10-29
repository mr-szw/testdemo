package com.dawei.test.demo.leetcode;


import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;

import net.sf.ehcache.search.aggregator.Count;

/**
 * 1365. 有多少小于当前数字的数字
 * https://leetcode-cn.com/problems/how-many-numbers-are-smaller-than-the-current-number/
 *
 * @author sinbad on 2020/08/19.
 */
public class Question1365 {


	public static void main(String[] args) {


		System.out.println(new Gson().toJson(new Question1365().smallerNumbersThanCurrent(new int[]{8, 1, 2, 2, 3})));
	}



	public int[] smallerNumbersThanCurrent(int[] nums) {
		if (nums == null) {
			return null;
		}
		int length = nums.length;
		int[] newNums = Arrays.copyOf(nums, length);
		Arrays.sort(newNums);
		int[] markAry = new int[101];

		//记录出现次数
		for (int num : newNums) {
			markAry[num] = markAry[num] + 1;
		}
		//累计小数出现次数
		int count = 0;
		int last = -1;
		for (int newNum : newNums) {
			if (last == newNum) {
				continue;
			} else {
				last = newNum;
			}
			int temp = markAry[newNum];
			markAry[newNum] = count;
			count += temp;
		}
		//读值
		int[] result = new int[length];
		for (int i = 0; i < length; i++) {
			result[i] = markAry[nums[i]];
		}
		return result;
	}


	//public int[] smallerNumbersThanCurrent(int[] nums) {
	//
	//		if (nums == null) {
	//			return null;
	//		}
	//
	//		int[] result = new int[nums.length];
	//		Map<Integer, Integer> numCountMap = new HashMap<>();
	//		Set<Integer> recordSet = new HashSet<>(nums.length);
	//		for (int num : nums) {
	//			if (!recordSet.add(num)) {
	//				continue;
	//			}
	//			for (int i : nums) {
	//				if (num > i) {
	//					numCountMap.put(num, numCountMap.getOrDefault(num, 0) + 1);
	//				}
	//			}
	//		}
	//		for (int i = 0; i < nums.length; i++) {
	//			result[i] = numCountMap.getOrDefault(nums[i], 0);
	//		}
	//		return result;
	//	}
}

