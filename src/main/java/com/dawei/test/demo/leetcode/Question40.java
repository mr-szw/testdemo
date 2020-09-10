package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.google.gson.Gson;

/**
 * 40. 组合总和 II
 * https://leetcode-cn.com/problems/combination-sum-ii/
 *
 * @author sinbad on 2020/08/19.
 */
public class Question40 {


	public static void main(String[] args) {

		int[] position = new int[]{2, 5, 2, 1, 2};
		System.out.println(new Gson().toJson(new Question40().combinationSum2(position, 5)));
	}

	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(candidates);
		Stack<Integer> subList = new Stack<>();
		combinationSum2(0, candidates, target, result, subList );
		return result;
	}

	public void combinationSum2(int start, int[] candidates, int target, List<List<Integer>> result, Stack<Integer> subList ) {

		if (target < 0) {
			return;
		}
		if (0 == target) {
			System.out.println(new Gson().toJson(subList));
			result.add(new ArrayList<>(subList));
			return;
		}

		for (int i = start; i < candidates.length; i++) {
			int candidate = candidates[i];
			if (candidate > target) {
				break;
			}

			// 小剪枝：同一层相同数值的结点，从第 2 个开始，候选数更少，结果一定发生重复，因此跳过，用 continue
			if (i > start && candidates[i] == candidates[i - 1]) {
				continue;
			}

			//继续
			subList.push(candidate);
			System.out.println("push >> " + candidate + "  target >>" + (target - candidate));
			combinationSum2(i + 1, candidates, target - candidate, result, subList);
			Integer pop = subList.pop();
			System.out.println("pop >> " + pop + "   target >> " + (target));
			//break;
		}
	}

}
