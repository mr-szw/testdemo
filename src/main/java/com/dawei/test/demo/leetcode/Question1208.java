package com.dawei.test.demo.leetcode;

import com.google.gson.Gson;

/**
 * 1208. 尽可能使字符串相等
 * https://leetcode-cn.com/problems/get-equal-substrings-within-budget/
 *
 * @author sinbad on 2021/2/6.
 * <p>
 */
public class Question1208 {


	public static void main(String[] args) {

		System.out.println(new Gson().toJson(new Question1208().equalSubstring("", "", 5)));
	}


	public int equalSubstring(String s, String t, int maxCost) {

		//先统计由 s -> t 每个字符的花费
		int length = s.length();
		int[] countArray = new int[length];

		for (int i = 0; i < length; i++) {
			countArray[i] = Math.abs(s.charAt(i) - t.charAt(i));
		}

		int left = 0;
		int right = 0;
		int count = 0;
		int maxLength = 0;
		for (int i : countArray) {
			count += i;
			while (maxCost < count) {
				count -= countArray[left];
				left++;
			}
			right++;
			maxLength = Math.max(maxLength, right - left);
		}
		return maxLength;
	}

}
