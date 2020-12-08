package com.dawei.test.demo.leetcode;

import java.util.Arrays;
import java.util.Queue;

import com.google.gson.Gson;

/**
 * 976. 三角形的最大周长
 * https://leetcode-cn.com/problems/largest-perimeter-triangle/
 *
 * @author sinbad on 2020/11/29.
 */
public class Question976 {

	public int largestPerimeter(int[] A) {
		Arrays.sort(A);
		for (int i = A.length - 1; i > 1; i--) {
			if (A[i - 1] + A[i - 2] > A[i]) {
				return A[i] + A[i - 1] + A[i - 2];
			}
		}
		return 0;
	}
}
