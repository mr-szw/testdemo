package com.dawei.test.demo.leetcode;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.Gson;

/**
 * 136. https://leetcode-cn.com/problems/single-number/
 *
 * @author sinbad on 2020/12/18.
 */
public class Question136 {

	public static void main(String[] args) {

		System.out.println(new Gson().toJson(new Question136().singleNumber(new int[]{})));
	}

	public int singleNumber(int[] nums) {
		int result = nums[0];
		for (int i = 1; i < nums.length; i++) {
			result = result ^ nums[i];
		}
		return result;
	}
}
