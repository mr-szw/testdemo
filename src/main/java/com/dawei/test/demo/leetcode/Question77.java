package com.dawei.test.demo.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * 77. 组合
 * https://leetcode-cn.com/problems/combinations/
 *
 * @author sinbad on 2020/08/19.
 */
public class Question77 {


	public static void main(String[] args) {

		System.out.println(new Question77().combine(1, 1));
	}

	public List<List<Integer>> combine(int n, int k) {
		if (n < k || k == 0) {
			return Collections.emptyList();
		}
		List<List<Integer>> resultList = new ArrayList<>();
		//Deque<Integer> subArray = new ArrayDeque<>();
		 Integer[] subArray = new Integer[k];

		//dfs(n, k, 1, subArray, resultList);
		combine(resultList, subArray, 0, 1, n, k);

		return resultList;
	}


	public void combine(List<List<Integer>> resultList, Integer[] subArray, int curLen, int leftNum, int element, int total) {
		if (curLen == total) {
			resultList.add(new ArrayList<>(Arrays.asList(subArray)));
			return;
		}
		for (int i = leftNum; i <= element - (total - curLen) + 1; i++) {
			subArray[curLen] = i;
			combine(resultList, subArray,curLen + 1, i + 1, element, total);
		}
	}

	private void dfs(int n, int k, int begin, Deque<Integer> path, List<List<Integer>> res) {
		if (path.size() == k) {
			res.add(new ArrayList<>(path));
			return;
		}
		for (int i = begin; i <= n; i++) {
			path.addLast(i);
			System.out.println("递归之前 => " + path);
			dfs(n, k, i + 1, path, res);
			path.removeLast();
			System.out.println("递归之后 => " + path);
		}
	}

}
