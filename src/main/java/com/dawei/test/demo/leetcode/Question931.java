package com.dawei.test.demo.leetcode;

import java.util.Random;

/**
 * 11. 盛最多水的容器 https://leetcode-cn.com/problems/container-with-most-water/
 *
 * @author sinbad on 2020/11/12.
 */
public class Question931 {

	public static void main(String[] args) {
		char[][] grid = new char[5][4];

		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				grid[i][j] = (char) random.nextInt(1);
			}
		}
		//[[2,1,3],[6,5,4],[7,8,9]]
		System.out.println(new Question931().minFallingPathSum(new int[][]{{2, 1, 3}, {6, 5, 4}, {7, 8, 9}}));
	}

	public int minFallingPathSum(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		int[][] dp = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0) {
					dp[0][j] = matrix[0][j];
				} else  {
					int min = 0;
					if (j > 0 && j < n - 1) {
						min = Math.min(dp[i - 1][j - 1], dp[i - 1][j + 1]);
						min = Math.min(min, dp[i - 1][j]);
					} else if (j > 0) {
						//此时j = n - 1
						min = Math.min(dp[i - 1][j - 1], dp[i - 1][j]);
					} else if (j < n - 1) {
						//此时 j == 0
						min = Math.min(dp[i - 1][j + 1], dp[i - 1][j]);
					}
					dp[i][j] = min + matrix[i][j];
				}
			}
		}

		int min = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			min = Math.min(min, dp[m - 1][i]);
		}
		return min;
	}
}
