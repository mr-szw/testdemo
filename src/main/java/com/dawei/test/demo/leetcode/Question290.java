package com.dawei.test.demo.leetcode;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.Gson;

/**
 * 290. 单词规律 https://leetcode-cn.com/problems/word-pattern/
 *
 * @author sinbad on 2020/12/16.
 */
public class Question290 {

	public static void main(String[] args) {

		System.out.println(new Gson().toJson(new Question290().wordPattern("abba", "dog dog dog dog")));
	}

	public boolean wordPattern(String pattern, String s) {
		// 感觉是编码的样子
		char[] charArray = pattern.toCharArray();
		String[] split = s.split(" ");
		if (charArray.length != split.length) {
			return false;
		}
		String[] valueArray = new String[26];
		Set<String> valueCacheSet = new HashSet<>();
		for (int i = 0; i < charArray.length; i++) {
			int index = charArray[i] - 'a';
			String value = valueArray[index];
			if (value == null) {
				if (!valueCacheSet.add(split[i])) {
					return false;
				}
				valueArray[index] = split[i];

			} else {
				if (!value.equals(split[i])) {
					return false;
				}
			}
		}
		return true;
	}
}
