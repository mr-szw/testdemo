package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 746. 使用最小花费爬楼梯
 * https://leetcode-cn.com/problems/min-cost-climbing-stairs/
 *
 * @author sinbad on 2020/09/18.
 * 技巧好多 不会玩
 * <p>
 */
public class Question746 {


	public static void main(String[] args) {

		System.out.println(new Question746().minCostClimbingStairs(new int[]{1, 0 , 0 , 0}));
	}

	public int minCostClimbingStairs(int[] cost) {

		int length = cost.length;

		int[] countAry = new int[length + 1];
		countAry[0] = 0;
		countAry[1] = 0;

		for (int i = 2; i <= length; i++) {
			countAry[i] = Math.min(countAry[i - 1] + cost[i - 1], countAry[i - 2] + cost[i - 2]);
		}
		return countAry[length];
	}
}