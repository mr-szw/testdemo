package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.google.gson.Gson;

/**
 * 40. 组合总和 II
 * https://leetcode-cn.com/problems/combination-sum-ii/
 *
 * @author sinbad on 2020/08/19.
 */
public class Question349 {


	public static void main(String[] args) {

		int[] position = new int[]{1, 2, 1, 2};
		System.out.println(new Gson().toJson(new Question349().intersection(position, new int[]{2,2})));
	}

//	public int[] intersection(int[] nums1, int[] nums2) {
//		//Set
//		Set<Integer> resultSetTemp = new HashSet<>();
//		Set<Integer> resultSet = new HashSet<>();
//		for (int j : nums1) {
//			resultSetTemp.add(j);
//		}
//		for (int j : nums2) {
//			if (resultSetTemp.contains(j)) {
//				resultSet.add(j);
//			}
//
//		}
//		Iterator<Integer> iterator = resultSet.iterator();
//		int[] resultInt = new int[resultSet.size()];
//		int num = 0;
//		while (iterator.hasNext()) {
//			resultInt[num++] = iterator.next();
//		}
//		return resultInt;
//	}


//	public int[] intersection(int[] nums1, int[] nums2) {
//		//Map
//		Map<Integer, Integer> resultMap = new HashMap<>();
//		for (int j : nums1) {
//			Integer orDefault = resultMap.getOrDefault(j, 0);
//			if (orDefault == 0) {
//				resultMap.put(j,  1);
//			}
//		}
//		int num = 0;
//		int[] resultInt = new int[resultMap.size()];
//		for (int j : nums2) {
//			Integer orDefault = resultMap.getOrDefault(j, 0);
//			if (orDefault == 1) {
//				resultInt[num++] = j;
//				resultMap.put(j, 2);
//			}
//		}
//		return Arrays.copyOf(resultInt, num);
//	}


	public int[] intersection(int[] nums1, int[] nums2) {
		//双指针
		int lastNum = Integer.MIN_VALUE;
		int count = nums1.length;
		int[] resultInt = new int[count];

		int count1 = count;
		count = 0;
		int count2 = nums2.length;
		int index1 = 0, index2 = 0;
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		while (index1 < count1 && index2 < count2) {
			int num1 = nums1[index1];

			int num2 = nums2[index2];
			if (num1 < num2) {
				index1++;
			} else if (num1 > num2) {
				index2++;
			} else {
				if (num1 == lastNum) {
					index1++;
					index2++;
				} else {
					lastNum = num1;
					resultInt[count++] = num1;
				}
			}
		}
		return Arrays.copyOf(resultInt, count);
	}
}
