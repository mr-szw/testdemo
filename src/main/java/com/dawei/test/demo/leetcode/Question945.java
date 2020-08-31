package com.dawei.test.demo.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

/**
 * https://leetcode-cn.com/problems/minimum-increment-to-make-array-unique/
 * 使数组中每个值唯一的最小增量
 *
 * @author sinbad on 2020/08/26.
 */
public class Question945 {


	public static void main(String[] args) {
		Random random = new Random(10);
		int len = random.nextInt(10);
		int[] A = new int[len];

		for (int i = 0; i < len; i++) {
			A[i] = random.nextInt(16);
		}


		System.out.println(new Question945().minIncrementForUnique(A));
	}

	public int minIncrementForUnique(int[] A) {
		int len = A.length;
		if (len == 0) {
			return 0;
		}
		//对A排序
		Arrays.sort(A);

		int sum = 0;
		int min = A[0];

		for (int i = 1; i < len; i++) {
			int addNum = 0;
			if (A[i] == min) {
				addNum = 1;

			}
			if (A[i] <= min) {
				addNum = min - A[i] + 1;
			}
			min = A[i] + addNum;
			sum += addNum;
		}

		return sum;

	}


}
