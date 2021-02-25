package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import org.checkerframework.checker.units.qual.min;

/**
 * 322. 零钱兑换 https://leetcode-cn.com/problems/coin-change/
 *
 * @author sinbad on 2021/08/24.
 */
public class Question332 {

	public static void main(String[] args) {
		System.out.println("sss");
		System.out.println(new Question332().coinChange(new int[] { 186, 419, 83, 408 }, 6249));
		// System.out.println(new Question332().coinChange(new int[] { 1 }, 1));
	}

	private int min = Integer.MAX_VALUE;
	// private int tempCountMin = Integer.MAX_VALUE;

	public int coinChange(int[] coins, int amount) {
		// 回溯加剪枝
		Arrays.sort(coins);

		coinChange(coins, 0, amount, coins.length - 1);
		if (min != Integer.MAX_VALUE) {
			return min;
		}
		return -1;
	}

	private void coinChange(int[] coins, int tempCountMin, int needCount, int userIndex) {
		if (tempCountMin > min) {
			return;
		}
		if (needCount < 0) {
			return;
		}
		if (needCount == 0) {
			min = Math.min(tempCountMin, min);
		}

		while (userIndex >= 0) {
			int useNum = coins[userIndex];
			if (useNum <= needCount) {
				coinChange(coins, tempCountMin + 1, needCount - useNum, userIndex);
			}
			userIndex--;
		}
	}



}
