package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/sudoku-solver/
 * 37. 解数独
 *
 * @author sinbad on 2020/09/15.
 */
public class Question37 {

	public static void main(String[] args) {

	}

	public void solveSudoku(char[][] board) {
		Map<Integer, List<Character>> rowMap = new HashMap<>();
		Map<Integer, List<Character>> colMap = new HashMap<>();
		for (int col = 0; col < 9; col++) {
			for (int row = 0; row < 9; row++) {
				List<Character> colHadNumList = colMap.get(row);
				if (colHadNumList == null) {
					colHadNumList = new ArrayList<>();
				}
				colHadNumList.add(board[col][row]);
				colMap.put(row, colHadNumList);
				List<Character> rowHadNumList = colMap.get(col);
				if (rowHadNumList == null) {
					rowHadNumList = new ArrayList<>();
				}
				rowHadNumList.add(board[col][row]);
				rowMap.put(col, rowHadNumList);
			}
		}
	}


}
