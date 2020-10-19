package com.dawei.test.demo.leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * 977. 有序数组的平方
 * https://leetcode-cn.com/problems/squares-of-a-sorted-array/
 *
 * @author sinbad on 2020/10/16.
 */
public class Question977 {


	public static void main(String[] args) {

		System.out.println(new Question977().sortedSquares(new int[]{-7, -3, 2, 3, 11}));
	}


	public int[] sortedSquares(int[] A) {

		//方法1
		//小于零 计算入栈
		// 大于零 合并

		//方法2
		//双指针
		int length = A.length;
		int[] result = new int[length];
		int left = 0;
		int right = length - 1;
		int rightIndex = right;
		while (rightIndex >= 0) {
			if (A[left] * A[left] >= A[right] * A[right]) {
				result[rightIndex--] = A[left] * A[left];
				left++;
			} else {
				result[rightIndex--] = A[right] * A[right];
				right--;
			}
		}
		return result;
	}

	public int[] sortedSquares1(int[] A) {
		//方法1
		//小于零 计算入栈
		// 大于零 合并

		//方法2
		Stack<Integer> numStack = new Stack<>();

		int length = A.length;
		int[] result = new int[length];

		int resultIndex = 0;
		for (int i = 0; i < length; i++) {
			int num = A[i];
			if (num < 0) {
				numStack.push(num * num);
			} else {
				int setNum = num * num;
				while (!numStack.isEmpty()) {
					if (numStack.peek() < setNum) {
						result[resultIndex++] = numStack.pop();
					} else {
						break;
					}
				}
				if (numStack.isEmpty() || numStack.peek() > setNum) {
					result[resultIndex++] = setNum;
				}
			}
		}

		while (!numStack.isEmpty()) {
			result[resultIndex++] = numStack.pop();
		}
		return result;
	}


}
