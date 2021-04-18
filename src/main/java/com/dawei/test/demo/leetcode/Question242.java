package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.google.gson.Gson;

/**
 * 242. 有效的字母异位词
 * https://leetcode-cn.com/problems/valid-anagram/
 *
 * @author sinbad on 2020/11/22.
 * <p>
 */
public class Question242 {


	public static void main(String[] args) {

		System.out.println(new Gson().toJson(new Question242().isAnagram("ASDSB", "sdcc")));
	}

	public boolean isAnagram(String s, String t) {

		if (s.length() != t.length()) {
			return false;
		}

		char[] charsS = s.toCharArray();
		char[] charsT = t.toCharArray();
		int[] countChar = new int[26];

		for (int i = 0; i < s.length(); i++) {
			countChar[charsS[i] - 'a']++;
			countChar[charsT[i] - 'a']--;
		}

		for (int i : countChar) {
			if (i != 0) {
				return false;
			}
		}
		return true;
	}
}
