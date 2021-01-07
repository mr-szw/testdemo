package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.checkerframework.checker.units.qual.A;

import com.google.gson.Gson;

/**
 * 39. 组合总和
 * https://leetcode-cn.com/problems/combination-sum/
 *
 * @author sinbad on 2020/09/09.
 * <p>
 */
public class Question39 {


	public static void main(String[] args) {

		System.out.println(new Gson().toJson(new Question39().solveNQueens(5)));
	}

	public List<List<String>> solveNQueens(int n) {
		int[] position = new int[]{2,  6, 7,  9, 10};
		List<List<String>> result = new ArrayList<>();
		System.out.println(new Gson().toJson(combinationSum(position, 14)));
		return result;

	}

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(candidates);
		for (int i = 0; i < candidates.length; i++) {
			Stack<Integer> subList = new Stack<>();
			combinationSum(i, candidates, target, result, subList);
		}
		return result;
	}

	public void combinationSum(int start, int[] candidates, int target, List<List<Integer>> result, Stack<Integer> subList) {
		for (int i = start; i < candidates.length; i++) {
			int candidate = candidates[i];
			if (candidate == target) {
				subList.push(candidate);
				result.add(new ArrayList<>(subList));
				subList.pop();
				break;
			} else if (candidate > target) {
				if (subList.empty()) {
					return;
				}
				//回退
				target = target + subList.pop();
				combinationSum(i + 1, candidates, target, result, subList);
			} else {
				//继续
				subList.push(candidate);
				combinationSum(i + 1, candidates, target - candidate, result, subList);
			}
		}


	}
}
