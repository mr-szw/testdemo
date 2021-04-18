package com.dawei.test.demo.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * https://leetcode-cn.com/problems/fair-candy-swap/
 * @author sinbad on 2021/2/1.
 */
public class Question888 {





	public int[] fairCandySwap(int[] A, int[] B) {
		//求总合 求半值
		int aCount = Arrays.stream(A).sum();
		int bCount = Arrays.stream(B).sum();

		int target = (aCount + bCount) / 2;
		int[] result = new int[2];

		//存储数组B 以备查找
		Set<Integer> markSet = new HashSet<>();
		for (int i : B) {
			markSet.add(i);
		}

		//数据找目标值
		for (int i : A) {
			int need = target - (aCount - i);
			if (markSet.contains(need)) {
				result[0] = i;
				result[1] = need;
			}
		}
		return result;
	}
}
