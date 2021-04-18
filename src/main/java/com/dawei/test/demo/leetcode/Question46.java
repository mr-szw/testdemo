package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/permutations/
 * 全排列 数组中没有重复元素
=======
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/permutations/ 全排列 数组中没有重复元素
 * 
>>>>>>> dev
 * @author sinbad on 2020/08/30.
 */
public class Question46 {

	// 1, 2, 3, 4, 5, 6, 8, 9, 10, 12

	public static void main(String[] args) {

	}





	public List<List<Integer>> permute3(int[] nums) {

		Map<Integer, Boolean> usedMarkMap = new HashMap<>(nums.length);
		List<List<Integer>> resultList = new ArrayList<>();

		Stack<Integer> pathList = new Stack<>();
		permute(resultList, pathList, nums, usedMarkMap);

		return resultList;

	}

	// 常规操作
	public void permute(List<List<Integer>> resultList, Stack<Integer> pathList, int[] nums,
			Map<Integer, Boolean> usedMarkMap) {

		int length = nums.length;
		if (pathList.size() == length) {
			resultList.add(new ArrayList<>(pathList));
			return;
		}
		for (int i = 0; i < length; i++) {
			int num = nums[i];
			if (usedMarkMap.get(num) == null || !usedMarkMap.get(num)) {
				pathList.add(num);
				usedMarkMap.put(num, true);
				permute(resultList, pathList, nums, usedMarkMap);
				usedMarkMap.put(num, false);
				pathList.pop();
			}
		}

	}
}
