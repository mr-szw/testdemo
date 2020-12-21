package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dawei.test.demo.utils.GsonUtil;

/**
 * https://leetcode-cn.com/problems/rotate-image/
 * 48. 旋转图像
 *
 * @author sinbad on 2020/12/19.
 */
public class Question48 {


	public static void main(String[] args) {

		int[][] ints = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		new Question48().rotate(ints);
		System.out.println(GsonUtil.toJson(Arrays.toString(ints)));

	}

	public void rotate(int[][] matrix) {

		//顺时旋转90度
		/*

		00 - 1 - - 0n
		|			|
		|			1
		0			|
		|			|
		n0 — - 0 — nn
			  i,j

		[i][j] <- [n - j][i]
		[n - j][i] <- [n -i][n- j]
		[n -i][n- j] <- [j][n- i]
	    [j][n -i] <-[i][j]
		 */

		int m = matrix.length;

		for (int i = 0; i < m / 2; i++) {
			for (int j = 0; j < (m + 1) / 2; j++) {
				//存左上
				int temp = matrix[i][j];

				//左下放入左上 2
				matrix[i][j] = matrix[m - 1 - j][i];

				//右下放入左下 3
				matrix[m - 1 - j][i] = matrix[m - 1 - i][m - 1 - j];

				//右上放入右下 4
				matrix[m - 1 - i][m - 1 - j] = matrix[j][m - 1 - i];

				//左上放入右上角 1
				matrix[j][m - 1 - i] = temp;
			}
		}


	}

}
