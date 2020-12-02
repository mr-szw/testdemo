package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import com.google.gson.Gson;

/**
 * 493. 翻转对
 * https://leetcode-cn.com/problems/reverse-pairs/
 *
 * @author sinbad on 2020/08/19.
 */
public class Question493 {


	public static void main(String[] args) {

		int[] position = new int[]{2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647};
		System.out.println(new Gson().toJson(new Question493().reversePairs(position)));
	}

	public int reversePairs(int[] nums) {
		int count = 0;
		if (nums == null || nums.length == 1) {
			return 0;
		}
		Set<Long> doubleNumSet = new HashSet<>();
		Map<Long, List<Integer>> positionMap = new HashMap<>();
		for (int i = nums.length -1 ; i >= 0; i--) {
			List<Integer> doubleList;
			long thisDouble = (long) 2 * nums[i];
			if (doubleNumSet.add(thisDouble)) {
				doubleList = new ArrayList<>();
			} else {
				doubleList = positionMap.get(thisDouble);
			}
			doubleList.add(i);
			positionMap.put(thisDouble, doubleList);

			//判断是否比存入的数大




		}

		List<Long> doubleNumList = doubleNumSet.stream().sorted().collect(Collectors.toList());

		for (int i = 0; i < nums.length - 1; i++) {
			long lastNum = Long.MIN_VALUE;
			for (long doubleNum : doubleNumList) {
				if (nums[i] <= doubleNum || lastNum == doubleNum) {
					break;
				}
				lastNum = doubleNum;

				if (nums[i] > doubleNum) {
					List<Integer> indexList = positionMap.get((long) doubleNum);
					int index = 0;
					for (; index < indexList.size(); index++) {
						if (indexList.get(index) > i) {
							break;
						}
					}
					count += indexList.size() - index;
				}
			}

		}

		return count;
	}

}
