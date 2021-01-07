package com.dawei.test.demo.leetcode;

import java.util.Random;

/**
 * 11. 盛最多水的容器 https://leetcode-cn.com/problems/container-with-most-water/
 * 
 * @author sinbad on 2020/11/12.
 */
public class Question11 {

	public static void main(String[] args) {
		char[][] grid = new char[5][4];

		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				grid[i][j] = (char) random.nextInt(1);
			}
		}
		System.out.println(new Question11().maxArea(new int[] { 1, 3, 43, 5, 3 }));
	}

	public int maxArea(int[] height) {

		return 0;
	}

}
