package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 第k个排列
 * https://leetcode-cn.com/problems/permutation-sequence/
 *
 * @author sinbad on 2020/08/19.
 */
public class Question845 {


	public static void main(String[] args) {

		System.out.println(new Question845().longestMountain(new int[]{}));
	}


	public int longestMountain(int[] A) {

		int leftPoint = -1;

		int leftValue = -1;
		int rightValue = -1;
		int rightPoint = 0;

		boolean findRight = false;
		int lastNum = A[0];

		for (int i = 1; i < A.length; i++) {

			int currentNum = A[i];
			if (!findRight) {
				if (lastNum >= currentNum) {
					leftValue = currentNum;
					leftPoint = i;
					findRight = true;
				} else {
					continue;
				}
			} else {
				rightValue = currentNum;
				rightPoint = i;
			}
		}
		return rightPoint - rightPoint;

	}


}
