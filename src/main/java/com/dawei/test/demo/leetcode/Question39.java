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

		int[] position = new int[]{2, 3, 6, 7};
		List<List<String>> result = new ArrayList<>();
		System.out.println(new Gson().toJson(new Question39().combinationSum(position, 4)));
	}

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(candidates);
		//for (int i = 0; i < candidates.length; i++) {
		Stack<Integer> subList = new Stack<>();
		combinationSum(0, candidates, target, result, subList);
		//}
		return result;
	}

	public void combinationSum(int start, int[] candidates, int target, List<List<Integer>> result, Stack<Integer> subList) {

		for (int i = start; i < candidates.length; i++) {
			if (target < 0) {
				return;
			}
			int candidate = candidates[i];
			if (candidate == target) {
				subList.push(candidate);
				result.add(new ArrayList<>(subList));
				subList.pop();
				return;
			} else if (candidate > target) {
				break;
			} else {
				//继续
				subList.push(candidate);
				System.out.println("push >> " + candidate);
				combinationSum(i, candidates, target - candidate, result, subList);
				Integer pop = subList.pop();
				System.out.println("pop >> " + pop);
				//break;
			}
		}
	}
}
