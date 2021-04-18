package com.dawei.test.demo.leetcode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

/**
 * 228. 汇总区间
 * https://leetcode-cn.com/problems/summary-ranges/
 *
 * @author sinbad on 2021/1/10.
 */
public class Question228 {


	public static void main(String[] args) {


		System.out.println(new Gson().toJson(new Question228().summaryRanges(new int[]{0, 2, 3, 4, 6, 8, 9})));
	}

	public List<String> summaryRanges(int[] nums) {

		List<String> resultList = new ArrayList<>();

		int length = nums.length;
		int i = 0;
		int startIndex = 0;
		while (i < length - 1) {
			if (nums[i + 1] > nums[i] + 1) {
				if (i == startIndex) {
					resultList.add(String.valueOf(nums[i]));
				} else {
					resultList.add(nums[startIndex] + "->" + nums[i]);
				}
				startIndex = i + 1;
			}
			i++;
		}
		if (startIndex < length) {
			resultList.add(nums[startIndex] + ((length - startIndex > 1) ? ("->" + nums[length - 1]) : ""));
		}
		return resultList;
	}
}

