package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.logging.Handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 216. 组合总和 III
 * https://leetcode-cn.com/problems/combination-sum-iii/
 * 动态规划
 *
 * @author sinbad on 2020/09/11.
 */
public class Question216 {


	public static void main(String[] args) {
		System.out.println(new Gson().toJson(new Question216().combinationSum3(3, 7)));
	}


	public List<List<Integer>> combinationSum3(int k, int n) {
		List<List<Integer>> resultList = new ArrayList<>();
		Stack<Integer> subStack = new Stack<>();
		search(1, k, n, 0, resultList, subStack);
		return resultList;
	}

	public void search(int start, int len, int target, int index, List<List<Integer>> resultList, Stack<Integer> subStack) {
		if (target == 0 && len == subStack.size()) {
			resultList.add(new ArrayList<>(subStack));
			return;
		}

		for (int i = start; i <= 9 && i <= target; i++) {
			if (target - start < 0) {
				return;
			}
			subStack.push(i);
			search(i + 1, len, target - i, index + 1, resultList, subStack);
			subStack.pop();
		}
	}


}
