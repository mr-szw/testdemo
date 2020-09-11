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
		boolean[] stop = new boolean[]{true};
		search(1, k, n, resultList, subStack, stop);
		return resultList;
	}
	//三个数  k个数字  n -> target


	public void search(int start, int needNum, int target, List<List<Integer>> resultList, Stack<Integer> subStack, boolean[] stop) {
		if (target == 0 && needNum == 0) {
			resultList.add(new ArrayList<>(subStack));
			System.out.println("OKOKOKOKOKOKOKOKOKOKOKOK");
			Integer pop = subStack.pop();
			System.out.println("pop  >> " + pop);
			return;
		}
		if (target - start < 0) {

			return;
		}
		for (int i = start; i < 9; i++) {
			subStack.push(i);
			System.out.println("Push >> " + i);
			search(i + 1, needNum - 1, target - i, resultList, subStack, stop);
			Integer pop = subStack.pop();
			System.out.println("pop  >> " + pop);
			if (!stop[0]) {
				stop[0] = true;
				break;
			}
			System.out.println("############");
		}
	}


}
