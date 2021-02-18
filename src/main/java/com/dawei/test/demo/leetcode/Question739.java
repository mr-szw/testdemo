package com.dawei.test.demo.leetcode;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/daily-temperatures/
 * 739. 每日温度
 *
 * @author sinbad on 2021/2/1.
 */
public class Question739 {


	public static void main(String[] args) {

		int[] position = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
		int[][] ints = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		new Question739().dailyTemperatures(position);

	}


	//单调栈
	public int[] dailyTemperatures(int[] T) {
		int length = T.length;
		int[] result = new int[length];
		Stack<Integer> pathStack = new Stack<>();
		for(int i =0; i< length; i++) {
			//空直接入栈
			//不空比较
			while (!pathStack.isEmpty() && T[pathStack.peek()] < T[i]) {
				result[pathStack.peek()] = i - pathStack.pop();
			}
			pathStack.push(i);
		}
		return result;
	}

	//暴力
	public int[] dailyTemperatures0(int[] T) {
		int length = T.length;
		int[] result = new int[length];
		for (int i = length - 2; i >= 0; i--) {
			int nowNum = T[i];
			for (int j = i + 1; j < length; j++) {
				if (T[j] > nowNum) {
					result[i] = j - i;
					break;
					//当后面的值比我小 那么他后面都没有比他大的 那么必然后面也没有比我大的 剪枝
				} else if (result[j] == 0) {
					result[i] = 0;
					break;
				}
			}
		}
		return result;
	}

}
