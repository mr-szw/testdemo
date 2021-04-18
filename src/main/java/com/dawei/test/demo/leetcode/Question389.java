package com.dawei.test.demo.leetcode;

/**
 * 389. 找不同
 * https://leetcode-cn.com/problems/find-the-difference/
 *
 * @author sinbad on 2020/12/18.
 */
public class Question389 {


	public static void main(String[] args) {

		new Question389().findTheDifference("", "");
	}

//	public char findTheDifference(String s, String t) {
//
//		char[] chars = s.toCharArray();
//		if (chars.length == 0) {
//			return t.toCharArray()[0];
//		}
//		int[] oldInfo = new int[26];
//
//
//		for (char c : chars) {
//			oldInfo[c - 'a']++;
//		}
//
//		char[] charNew = t.toCharArray();
//		for (char c : charNew) {
//			int index = c - 'a';
//			oldInfo[index]--;
//			if (oldInfo[index] < 0) {
//				return c;
//			}
//		}
//		throw new RuntimeException("Not find");
//
//	}

	public char findTheDifference(String s, String t) {

		char[] chars = s.toCharArray();
		char[] charNew = t.toCharArray();

		int result = charNew[0];
		for (char c : chars) {
			result = result ^ c;
		}

		for (int i = 1; i < charNew.length; i++) {
			result = result ^ charNew[i];
		}
		return (char) result;
	}
}
