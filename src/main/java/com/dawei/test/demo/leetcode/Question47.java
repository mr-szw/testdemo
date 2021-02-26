package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.checkerframework.checker.units.qual.A;

import com.google.gson.Gson;

/**
 * 216. 组合总和 III https://leetcode-cn.com/problems/combination-sum-iii/ 动态规划
 *
 * @author sinbad on 2020/09/11.
 */
public class Question47 {

	public static void main(String[] args) {
		int[] ints = { 1, 2, 2 };
		System.out.println(new Gson().toJson(new Question47().permuteUnique(ints)));
	}

	public List<List<Integer>> permuteUnique(int[] nums) {

		List<List<Integer>> resultList = new ArrayList<>();
		boolean[] visitMark = new boolean[nums.length];

		// 排序为后续的操作做准备
		Arrays.sort(nums);

		Stack<Integer> addDataRecord = new Stack<>();
		permuteUnique(nums, visitMark, resultList, addDataRecord);

		return resultList;

	}

	public List<List<Integer>> permuteUnique(int[] nums, boolean[] visitMark,
			List<List<Integer>> resultList, Stack<Integer> addDataRecord) {
		if (addDataRecord.size() == nums.length) {
			resultList.add(new ArrayList<>(addDataRecord));
			return resultList;
		}
		for (int i = 0; i < nums.length; i++) {
			if (visitMark[i] || (i > 0 && nums[i] == nums[i - 1] && visitMark[i - 1])) {
				continue;
			}

			addDataRecord.push(nums[i]);
			visitMark[i] = true;
			permuteUnique(nums, visitMark, resultList, addDataRecord);
			visitMark[i] = false;
			addDataRecord.pop();
		}

		return resultList;
	}

}
