package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/permutations/
 * 全排列 数组中没有重复元素
 * @author sinbad on 2020/08/30.
 */
public class Question46 {

	// 1, 2, 3, 4, 5, 6, 8, 9, 10, 12

	public static void main(String[] args) {

		System.out.println(new Question46().permute(new int[]{}));
	}

	public List<List<Integer>> permute(int[] nums) {

		int num = 3;
		boolean[] usedMark = new boolean[num];
		List<List<Integer>> resultList = new ArrayList<>();
		List<Integer> pathList = new ArrayList<>();
		List<List<Integer>> permute = permute(resultList, pathList, num, 0, usedMark);

		return resultList;

	}



	//常规操作
	public List<List<Integer>> permute(List<List<Integer>> resultList, List<Integer> pathList, int len, int startIndex, boolean[] usedMark) {

		if (startIndex == len) {
			 resultList.add(new ArrayList<>(pathList));
			return resultList;
		}
		for(int i = startIndex; i < len; i++) {
			if (!usedMark[startIndex]) {
				usedMark[startIndex] = true;
				pathList.add(i);

				return permute(resultList, pathList, startIndex + 1,len, usedMark);
			}
		}

		return resultList;
	}
}
