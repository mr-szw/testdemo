package com.dawei.test.demo.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/sudoku-solver/
 * 37. 解数独
 *
 * @author sinbad on 2020/09/15.
 */
public class Question941 {

	public static void main(String[] args) {

		new Question941().validMountainArray(new int[]{3, 7, 20, 14, 15, 14, 10, 8, 2, 1});
		System.out.println("STOP");
	}

	public boolean validMountainArray(int[] A) {

		int length = A.length;
		if (length < 3) {
			return false;
		}
		int last = A[0];

		boolean up = true;
		int top = 0;
		for (int i = 1; i < length; i++) {
			if (up) {

				if (A[i] > last) {
					last = A[i];
				} else if (A[i] < last) {
					up = false;
					i = i - 1;
					top = i;

				} else {
					return false;
				}
			} else {
				if (A[i] < last) {
					last = A[i];
				} else {
					return false;
				}
			}
		}
		return !up && (top > 0 && top < length - 1);
	}


}
