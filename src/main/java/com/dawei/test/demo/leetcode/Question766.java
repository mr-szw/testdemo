package com.dawei.test.demo.leetcode;

/**
 * 766. 托普利茨矩阵
 * https://leetcode-cn.com/problems/toeplitz-matrix/
 *
 * @author sinbad on 2020/08/19.
 */
public class Question766 {


	public static void main(String[] args) {
	}

	public boolean isToeplitzMatrix(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;

		int i = 1;
		int j = 1;

		int zeroValue = matrix[0][0];
		while (i < m && j < n) {
			if (matrix[i++][j++] != zeroValue) {
				return false;
			}
		}
		return true;
	}
}
