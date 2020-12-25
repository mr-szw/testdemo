package com.dawei.test.demo.leetcode;

/**
 * 746. 使用最小花费爬楼梯
 * https://leetcode-cn.com/problems/min-cost-climbing-stairs/solution/ *
 *
 * @author sinbad on 2020/12/21.
 * dp
 */
public class Question746 {


	public static void main(String[] args) {
		System.out.println(new Question746().minCostClimbingStairs(new int[]{1, 0, 0, 0}));
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
