package com.dawei.test.demo.leetcode;

import java.util.Random;

/**
 * 岛屿数量
 *
 * @author sinbad on 2020/08/19.
 */
public class Question200 {


	public static void main(String[] args) {
		char[][] grid = new char[5][4];

		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				grid[i][j] = (char) random.nextInt(1);
			}
		}
		System.out.println(new Question200().numIslands(grid));
	}


	public int numIslands(char[][] grid) {


		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {

			}
		}

		return 0;
	}


}
