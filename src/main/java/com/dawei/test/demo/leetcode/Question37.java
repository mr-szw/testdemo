package com.dawei.test.demo.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.UUID;

/**
 * https://leetcode-cn.com/problems/sudoku-solver/
 * 37. 解数独
 *
 * @author sinbad on 2020/09/15.
 */
public class Question37 {

	public static void main(String[] args) {

		char[][] board = new char[][]{
				//0    1    2    3    4    5    6    7    8
				{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, //0
				{'6', '.', '.', '1', '9', '5', '.', '.', '.'}, //1
				{'.', '9', '8', '.', '.', '.', '.', '6', '.'}, //2
				{'8', '.', '.', '.', '6', '.', '.', '.', '3'}, //3
				{'4', '.', '.', '8', '.', '3', '.', '.', '1'}, //4
				{'7', '.', '.', '.', '2', '.', '.', '.', '6'}, //5
				{'.', '6', '.', '.', '.', '.', '2', '8', '.'}, //6
				{'.', '.', '.', '4', '1', '9', '.', '.', '5'}, //7
				{'.', '.', '.', '.', '8', '.', '.', '7', '9'}  //8

		};
		new Question37().solveSudoku(board);
		System.out.println("STOP");
	}

	public void solveSudoku(char[][] board) {
		Map<Integer, Set<Character>> rowMap = new HashMap<>();
		Map<Integer, Set<Character>> colMap = new HashMap<>();
		for (int col = 0; col < 9; col++) {
			for (int row = 0; row < 9; row++) {
				char thisNum = board[col][row];
				if ('.' == thisNum) {
					continue;
				}
				Set<Character> colHadNumList = colMap.get(row);
				if (colHadNumList == null) {
					colHadNumList = new HashSet<>();
				}
				colHadNumList.add(thisNum);
				colMap.put(row, colHadNumList);

				Set<Character> rowHadNumList = rowMap.get(col);
				if (rowHadNumList == null) {
					rowHadNumList = new HashSet<>();
				}
				rowHadNumList.add(thisNum);
				rowMap.put(col, rowHadNumList);
			}
		}


		Stack<Character> putHistory = new Stack<>();
		tryPutNum(board, colMap, rowMap, 0, 0, putHistory);


	}

	private boolean tryPutNum(char[][] board, Map<Integer, Set<Character>> colMap,
							  Map<Integer, Set<Character>> rowMap, int startRow, int startCol, Stack<Character> putHistory) {
		if (startCol == 9 && startRow == 9) {
			return true;
		}

		int col = startCol;
		int row = startRow;
		for (; col < 9; col++) {
			for (; row < 9; row++) {
				if ('.' != board[col][row]) {
					continue;
				}
				for (char num = '1'; num <= '9'; num++) {
					boolean canPut = checkThisBoxCanPut(board, colMap, rowMap, num, row, col);
					if (canPut) {
						putHistory.push(num);
						putNumInBoard(board, colMap, rowMap, num, row, col);
						boolean ok = tryPutNum(board, colMap, rowMap, row, col, putHistory);
						if (ok) {
							return true;
						}
						putHistory.pop();
						removeNumInBoard(board, colMap, rowMap, num, row, col);
					}
				}
				return false;
			}
			row = 0;
		}

		return col == 9;
	}

	private void putNumInBoard(char[][] board, Map<Integer, Set<Character>> colMap,
							   Map<Integer, Set<Character>> rowMap, char num, int row, int col) {
		board[col][row] = num;
		colMap.get(row).add(num);
		rowMap.get(col).add(num);
	}

	private void removeNumInBoard(char[][] board, Map<Integer, Set<Character>> colMap,
								  Map<Integer, Set<Character>> rowMap, char num, int row, int col) {
		board[col][row] = '.';
		colMap.get(row).remove(num);
		rowMap.get(col).remove(num);
	}


	private boolean checkThisBoxCanPut(char[][] board, Map<Integer, Set<Character>> colMap,
									   Map<Integer, Set<Character>> rowMap, char num, int row, int col) {

		if (rowMap.get(col).contains(num) || colMap.get(row).contains(num)) {

			return false;
		}
		int boxRow = 2;
		int boxCol = 2;


		if (row > 5) {
			boxRow = 8;
		} else if (row > 2) {
			boxRow = 5;
		}
		if (col > 5) {
			boxCol = 8;
		} else if (col > 2) {
			boxCol = 5;
		}


		for (int colInBox = boxCol - 2; colInBox <= boxCol; colInBox++) {
			for (int rowInBox = boxRow - 2; rowInBox <= boxRow; rowInBox++) {
				if (board[colInBox][rowInBox] == num) {
					System.out.println("num " + num + " colInBox = " + colInBox + "  rowInBox=  " + rowInBox + "  board " + board[colInBox][rowInBox]);
					return false;
				}
			}
		}
		return true;
	}


}
