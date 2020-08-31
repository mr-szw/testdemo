package com.dawei.test.demo.leetcode;

import java.util.Random;

/**
 * https://leetcode-cn.com/problems/reverse-words-in-a-string-iii/
 *
 * 反转字符串中的单词 III
 * @author sinbad on 2020/08/30.
 */
public class Question557 {


	public static void main(String[] args) {


		String s = "Let's  take LeetCode contes";

		System.out.println(new Question557().reverseWords(s));
	}


	public String reverseWords(String s) {

		StringBuilder result = new StringBuilder();

		char[] chars = s.toCharArray();
		StringBuilder resultTemp = new StringBuilder();
		for (char aChar : chars) {
			if (aChar == ' ') {
				result.append(resultTemp.reverse().append(aChar).toString());
				resultTemp = new StringBuilder();
			} else {
				resultTemp.append(aChar);
			}
		}
		if (!resultTemp.toString().equals("")) {
			result.append(resultTemp.reverse().toString());
		}
		return result.toString();

	}


}
