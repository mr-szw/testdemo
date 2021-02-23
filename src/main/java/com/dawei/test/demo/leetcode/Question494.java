package com.dawei.test.demo.leetcode;

/**
 * 494. 目标和 https://leetcode-cn.com/problems/target-sum/
 *
 * @author sinbad on 2021/1/3. 动态规划
 */
public class Question494 {

	public static void main(String[] args) {

		System.out.println(new Question494().findTargetSumWays(new int[] {}, 1));
	}

	// 暴力遍历
	// 动态规划
	public int findTargetSumWays(int[] nums, int S) {
		/**
		 *		子问题 第一位和后面的相加的S
		 *	选择 第0/i位 选择—+	
		 */
		int[][] dp = new int[nums.length][2];

		//0 选 +
		//1 选 -
		dp[0][0] = nums[0]; 
		dp[0][1] = -nums[0]; 
		
		for (int i = 1; i < nums.length; i++) {
			dp[i][0] = 1;
		}
		
		
		
		
		
		
	
		return 1;

	}
}
