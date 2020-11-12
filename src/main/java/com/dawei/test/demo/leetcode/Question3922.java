package com.dawei.test.demo.leetcode;

import java.util.Stack;

/**
 * 992. https://leetcode-cn.com/problems/sort-array-by-parity-ii/
 * 922. 按奇偶排序数组 II
 * @author sinbad on 2020/11/12.
 */
public class Question3922 {

	public static void main(String[] args) {
		int len = 1;
		int[] nums = new int[] { 4, 1, 1, 0, 1, 0 };
		System.out.println(new Question3922().sortArrayByParityII(nums));
	}

	public int[] sortArrayByParityII(int[] A) {
		// 双指针
		int length = A.length;
		// 偶数
		int evenIndex = 0;
		// 奇数
		int oddIndex = 1;
		while (evenIndex < length) {
			// 偶数
			if (A[evenIndex] % 2 != 0) {
				// 奇数
				while (A[oddIndex] % 2 != 0) {
					oddIndex += 2;
				}
				int temp = A[oddIndex];
				A[oddIndex] = A[evenIndex];
				A[evenIndex] = temp;
			}
			evenIndex += 2;
		}
		return A;
	}

	public int[] sortArrayByParityII2(int[] A) {
		// 奇数
		Stack<Integer> oddStack = new Stack<>();
		// 偶数
		Stack<Integer> evenStack = new Stack<>();

		boolean evenCheck = true;
		for (int i = 0; i < A.length; i++) {
			// 偶数
			if (evenCheck) {
				if (A[i] % 2 != 0) {
					// 获取奇数下标
					oddStack.add(i);
				}
			} else {
				if (A[i] % 2 == 0) {
					evenStack.add(i);
				}
			}
			evenCheck = !evenCheck;
		}

		while (!evenStack.isEmpty()) {
			Integer popIndex = evenStack.pop();
			Integer oddIndex = oddStack.pop();
			int temp = A[popIndex];
			A[popIndex] = A[oddIndex];
			A[oddIndex] = temp;

		}
		return A;
	}

}
