package com.dawei.test.demo.leetcode;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

/**
 * 40. 组合总和 II https://leetcode-cn.com/problems/combination-sum-ii/
 *
 * @author sinbad on 2020/08/19.
 */
public class Question454 {

	public static void main(String[] args) {

		System.out.println(new Gson().toJson(new Question454().fourSumCount(new int[] { 1, 2 },
				new int[] { -2, -1 }, new int[] { 1, 2 }, new int[] { 0, 2 })));
	}

	public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {

		int count = 0;
		Map<Integer, Integer> countAB = new HashMap<>();
		for (int i : A) {
			for (int j : B) {
				Integer orDefault = countAB.getOrDefault(i + j, 0);
				countAB.put(i + j, orDefault + 1);
			}
		}
		for (int i : C) {
			for (int j : D) {
				count += countAB.getOrDefault(-i - j, 0);

			}
		}
		return count;
	}

	public int getCanResultNum(int result, int[] array) {
		// 回溯 剪不了还
		int count = 0;
		int lastNum = Integer.MAX_VALUE;
		for (int i : array) {
			if (i == lastNum) {
				count++;
			}
			if (result + i == 0) {
				count++;
				lastNum = i;
			}
			if (result + i > 0) {
				break;
			}
		}

		return count;
	}

}
