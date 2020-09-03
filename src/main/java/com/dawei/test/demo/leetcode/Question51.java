package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

/**
 * N 皇后
 * https://leetcode-cn.com/problems/n-queens/
 *
 * @author sinbad on 2020/09/2.
 *
 * 目前处理结果 仅能返回一次的计算结果
 */
public class Question51 {


	public static void main(String[] args) {

		System.out.println(new Gson().toJson(new Question51().solveNQueens(4)));
	}

	public List<List<String>> solveNQueens(int n) {


		int[] history = new int[n];

		int[][] position = new int[n][n];


		List<List<String>> result = new ArrayList<>();

		print(position, null);
		checkRow(result, n, position, 0, history, true);

//		int temp = 0;
//
//
//		while (history[0] < n) {
////
//			temp++;
//		}


		return result;

	}

	private void checkRow(List<List<String>> result, int n, int[][] position, int row, int[] historyCol, boolean next) {
		if (row == n) {
			recordResult(position, result);
			return;
		}
		int j = 0;
		if (!next) {
			j = historyCol[row];
		}
		boolean canPut = false;
		for (; j < n; j++) {
			if (position[row][0] == 1 || position[0][j] == 1) {
				continue;
			}
			canPut = checkCanPut(position, row, j);
			if (canPut) {
				break;
			}
		}
		if (!canPut) {
			row = row -1;
			putPoint(position, row, historyCol[row], false);
			historyCol[row] = historyCol[row] + 1;
			checkRow(result, n, position, row, historyCol, false);
		} else {
			historyCol[row] = j;
			putPoint(position, row, j, true);
			checkRow(result, n, position, row + 1, historyCol, true);
		}
	}


	public void recordResult(int[][] position, List<List<String>> result){
		List<String> subResultList = new ArrayList<>();
		for (int[] ints : position) {
			StringBuilder stringBuilder = new StringBuilder();
			for (int value : ints) {
				stringBuilder.append(value == 1 ? "Q" : ".");
			}
			subResultList.add(stringBuilder.toString());

		}
		result.add(subResultList);
	}

//}


	private boolean checkCanPut(int[][] position, int row, int col) {
		return position[row][col] == 0;
	}

	//放入皇后
	private void putPoint(int[][] position, int row, int col, boolean putFlag) {
		int length = position.length;


		position[row][col] = 1;

		int tempRow = row;
		//向上
		while (tempRow >= 0) {
			if (position[tempRow][col] != 1) {
				if (putFlag) {
					position[tempRow][col] += 2;
				} else {
					position[tempRow][col] -= 2;
				}
			}
			tempRow--;
		}

		tempRow = row;
		//向下
		while (tempRow < length) {
			if (position[tempRow][col] != 1) {
				if (putFlag) {
					position[tempRow][col] += 2;
				} else {
					position[tempRow][col] -= 2;
				}
			}
			tempRow++;
		}


		int tempCol = col;
		//向上
		while (tempCol >= 0) {
			if (position[row][tempCol] != 1) {
				if (putFlag) {
					position[row][tempCol] += 2;
				} else {
					position[row][tempCol] -= 2;
				}
			}

			tempCol--;
		}

		tempCol = col;
		//向下
		while (tempCol < length) {
			if (position[row][tempCol] != 1) {
				if (putFlag) {
					position[row][tempCol] += 2;
				} else {
					position[row][tempCol] -= 2;
				}
			}
			tempCol++;
		}

		tempRow = row;
		tempCol = col;

		//右上
		while (tempRow >= 0 && tempCol < length) {
			if (position[tempRow][tempCol] != 1) {
				if (putFlag) {
					position[tempRow][tempCol] += 2;
				} else {
					position[tempRow][tempCol] -= 2;
				}
			}
			tempRow--;
			tempCol++;
		}
		tempRow = row;
		tempCol = col;
		//左上
		while (tempRow < length && tempCol >= 0) {
			if (position[tempRow][tempCol] != 1) {
				if (putFlag) {
					position[tempRow][tempCol] += 2;
				} else {
					position[tempRow][tempCol] -= 2;
				}
			}
			tempRow++;
			tempCol--;
		}
		tempRow = row;
		tempCol = col;
		//右下
		while (tempRow < length && tempCol < length) {
			if (position[tempRow][tempCol] != 1) {
				if (putFlag) {
					position[tempRow][tempCol] += 2;
				} else {
					position[tempRow][tempCol] -= 2;
				}
			}
			tempRow++;
			tempCol++;
		}
		tempRow = row;
		tempCol = col;
		//左上
		while (tempRow >= 0 && tempCol >= 0) {
			if (position[tempRow][tempCol] != 1) {
				if (putFlag) {
					position[tempRow][tempCol] += 2;
				} else {
					position[tempRow][tempCol] -= 2;
				}
			}
			tempRow--;
			tempCol--;
		}

		if (!putFlag) {
			position[row][col] = 0;
		}
		print(position, putFlag);

	}


	int num = 0;

	private void print(int[][] position, Boolean putFlag) {
		int length = position.length;
		if (putFlag != null) {
			if (putFlag) {
				System.out.println("############### PUT  ############ num=" + num++);
			} else {
				System.out.println("--------- back    --------- back=" + --num);
			}
		}
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				System.out.print(" " + position[i][j]);
			}
			System.out.println(" ");
		}

		if (putFlag != null) {
			if (putFlag) {
				System.out.println("############### PUT  ############ num=" + num);
			} else {
				System.out.println("--------- back    --------- back=" + num);
			}
		}
		System.out.println(" ");
	}
}
