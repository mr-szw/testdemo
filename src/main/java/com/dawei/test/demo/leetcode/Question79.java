package com.dawei.test.demo.leetcode;

import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.checkerframework.checker.units.qual.A;

import com.google.gson.Gson;

/**
 * 79. 单词搜索
 * https://leetcode-cn.com/problems/word-search/
 *
 * @author sinbad on 2020/09/13.
 */
public class Question79 {


	public static void main(String[] args) {
		//char[][] board = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
		char[][] board = new char[][]{{'C', 'A', 'A'},{'A','A','A'},{'B','C','D'}};
		String word = "AAB";
		System.out.println(new Question79().exist(board, word));
	}

	public boolean exist(char[][] board, String word) {
		char[] chars = word.toCharArray();
		char firstNum = chars[0];
		int height = board.length; // 外层
		int weight = board[0].length; //内层 4
		boolean[][] history = new boolean[height][weight];
		for (int nowHeight = 0; nowHeight < height; nowHeight++) {
			for (int nowWeigh = 0; nowWeigh < weight; nowWeigh++) {
				if (board[nowHeight][nowWeigh] == firstNum) {
					history[nowHeight][nowWeigh] = true;
					boolean exist = exist(board, chars, 1, nowWeigh, nowHeight, weight, height, history);
					history[nowHeight][nowWeigh] = false;
					if (exist) {
						return true;
					}
				}
			}
		}
		return false;

	}

	public boolean exist(char[][] board, char[] chars, int nextWordIndex, int nowWeigh, int nowHeight, int weigh, int height, boolean[][] history) {

		if (nextWordIndex == chars.length) {
			return true;
		}

		//右
		if (nowWeigh + 1 < weigh) {
			if (chars[nextWordIndex] == board[nowHeight][nowWeigh + 1] && !history[nowHeight][nowWeigh + 1]) {
				history[nowHeight][nowWeigh + 1] = true;
				boolean check =  exist(board, chars, nextWordIndex + 1, nowWeigh + 1, nowHeight, weigh, height, history);
				if(check) {
					return true;
				}

			}
		}
		//左
		if (nowWeigh - 1 >= 0) {
			if (chars[nextWordIndex] == board[nowHeight][nowWeigh - 1] && !history[nowHeight][nowWeigh - 1]) {
				history[nowHeight][nowWeigh - 1] = true;
				boolean check =   exist(board, chars, nextWordIndex + 1, nowWeigh - 1, nowHeight, weigh, height, history);
				if(check) {
					return true;
				}
			}
		}
		//下
		if (nowHeight + 1 < height) {
			if (chars[nextWordIndex] == board[nowHeight + 1][nowWeigh] && !history[nowHeight + 1][nowWeigh]) {
				history[nowHeight + 1][nowWeigh] = true;
				boolean check =   exist(board, chars, nextWordIndex + 1, nowWeigh, nowHeight + 1, weigh, height, history);
				if(check) {
					return true;
				}
			}
		}
		//上
		if (nowHeight - 1 >= 0) {
			if (chars[nextWordIndex] == board[nowHeight - 1][nowWeigh] && !history[nowHeight - 1][nowWeigh]) {
				history[nowHeight - 1][nowWeigh] = true;
				boolean check =   exist(board, chars, nextWordIndex + 1, nowWeigh, nowHeight - 1, weigh, height, history);
				if(check) {
					return true;
				}
			}
		}
		history[nowHeight][nowWeigh] = false;
		return false;
	}
}
