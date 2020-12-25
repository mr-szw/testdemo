package com.dawei.test.demo.leetcode;

import com.google.gson.Gson;

/**
 * 316. 去除重复字母 https://leetcode-cn.com/problems/remove-duplicate-letters/
 *
 *
 * @author sinbad on 2020/12/20.
 */
public class Question316 {

	public static void main(String[] args) {

		System.out.println(new Gson().toJson(new Question316().removeDuplicateLetters("bcabc")));
	}

	/**
	 * 要求结果包含所有出现过的字符但是不能重复 通过一个是否加入可以保证仅一次 要求保证原本的出现的相对顺序且保证第一条 维护计数数组 确定最后添加机会
	 *
	 */
	public String removeDuplicateLetters(String s) {

		int[] mark = new int[26];
		char[] chars = s.toCharArray();
		// 统计字符出现次数
		for (char c : chars) {
			mark[c - 'a']++;
		}
		StringBuilder result = new StringBuilder();
		boolean[] visited = new boolean[26];
		for (char c : chars) {
			int index = c - 'a';
			// 没有存储
			if (!visited[index]) {
				while (result.length() > 0 && result.charAt(result.length() - 1) > c) {
					int lastChIndex = result.charAt(result.length() - 1) - 'a';
					// 后面还有这个自负
					if (mark[lastChIndex] > 0) {
						// 之后再添加
						visited[lastChIndex] = false;
						result.deleteCharAt(result.length() - 1);
					} else {
						break;
					}
				}
				result.append(c);
				visited[index] = true;
			}
			// 控制加入机会
			mark[index]--;
		}
		return result.toString();
	}
}
