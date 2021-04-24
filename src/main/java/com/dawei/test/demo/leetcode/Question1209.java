package com.dawei.test.demo.leetcode;

import java.util.Stack;

import com.google.gson.Gson;

/**
 *
 * https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string-ii/
 *
 * @author sinbad on 2021/1/6.
 */
public class Question1209 {

	public static void main(String[] args) {

		int[] position = new int[]{1, 2, 3, 4, 5};
		int[] positionOut = new int[]{4, 5, 3, 2, 1};
		System.out.println(new Gson().toJson(new Question1209().removeDuplicates("position", 12)));
	}
	public String removeDuplicates(String s, int k) {
		Stack<Pair> charStack = new Stack<>();

		char[] chars = s.toCharArray();
		for (char c : chars) {
			if (charStack.isEmpty() || charStack.peek().ch != c) {
				charStack.push(new Pair(c, 1));
			} else {
				Pair pop = charStack.pop();
				if (pop.count + 1 < k) {
					charStack.push(new Pair(c, pop.count + 1));
				}
			}
		}

		StringBuilder resultBuilder = new StringBuilder();
		while (!charStack.empty()) {
			Pair p = charStack.pop();
			for (int i = 0; i < p.count; i++) {
				resultBuilder.append(p.ch);
			}
		}
		return resultBuilder.reverse().toString();

	}

	static class Pair {
		private char ch;
		private int count;
		public Pair(char ch, int count) {
			this.ch = ch;
			this.count = count;
		}
	}
}
