package com.dawei.test.demo.leetcode;

import java.util.List;

/**
 * N 皇后
 * https://leetcode-cn.com/problems/n-queens/
 *
 * @author sinbad on 2020/09/2.
 */
public class Question51 {


	public static void main(String[] args) {

		new Question51().solveNQueens(8);
	}


	public List<List<String>> solveNQueens(int n) {

		int[][] position = new int[n][n];

		putPoint(position, 0, 0);

		for (int i = 1; i < n; i++) {
			for (int j = 0; j < n; j++) {

				if (position[i][0] == 1 || position[0][j] == 1) {
					continue;
				}
				boolean canPut = checkCanPut(position, i, j);
				if (canPut) {
					putPoint(position, i, j);
					break;
				}
			}
		}
		return null;

	}


	private boolean checkCanPut(int[][] position, int row, int col) {
		return position[row][col] == 0;
	}

	//放入皇后
	private void putPoint(int[][] position, int row, int col) {
		int length = position.length;


		position[row][col] = 1;

		int tempRow = row;
		//向上
		while (tempRow >= 0) {
			if (position[tempRow][col] != 1) {
				position[tempRow][col] = 2;
			}
			tempRow--;
		}

		tempRow = row;
		//向下
		while (tempRow < length) {
			if (position[tempRow][col] != 1) {
				position[tempRow][col] = 2;
			}
			tempRow++;
		}


		int tempCol = col;
		//向上
		while (tempCol >= 0) {
			if (position[row][tempCol] != 1) {
				position[row][tempCol] = 2;
			}

			tempCol--;
		}

		tempCol = col;
		//向下
		while (tempCol < length) {
			if (position[row][tempCol] != 1) {
				position[row][tempCol] = 2;
			}
			tempCol++;
		}

		tempRow = row;
		tempCol = col;

		//右上
		while (tempRow >= 0 && tempCol < length) {
			if (position[tempRow][tempCol] != 1) {
				position[tempRow][tempCol] = 2;
			}
			tempRow--;
			tempCol++;
		}
		tempRow = row;
		tempCol = col;
		//左上
		while (tempRow < length && tempCol >= 0) {
			if (position[tempRow][tempCol] != 1) {
				position[tempRow][tempCol] = 2;
			}
			tempRow++;
			tempCol--;
		}
		tempRow = row;
		tempCol = col;
		//右下
		while (tempRow < length && tempCol < length) {
			if (position[tempRow][tempCol] != 1) {
				position[tempRow][tempCol] = 2;
			}
			tempRow++;
			tempCol++;
		}
		tempRow = row;
		tempCol = col;
		//左上
		while (tempRow >= 0 && tempCol >= 0) {
			if (position[tempRow][tempCol] != 1) {
				position[tempRow][tempCol] = 2;
			}
			tempRow--;
			tempCol--;
		}


		print(position);

	}


	private void print(int[][] position) {
		int length = position.length;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				System.out.print(" " + position[i][j]);
			}
			System.out.println(" ");
		}
		System.out.println(" ");
	}
}
