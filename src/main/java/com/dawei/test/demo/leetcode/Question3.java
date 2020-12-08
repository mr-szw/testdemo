package com.dawei.test.demo.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 3. 无重复字符的最长子串 https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * 
 * @author sinbad on 2020/11/19.
 *
 *         滑动窗口问题
 */
public class Question3 {

	public static void main(String[] args) {
		String grid = "aabaab!bb";
		System.out.println(new Question3().lengthOfLongestSubstring(grid));
	}

	// public int lengthOfLongestSubstring(String s) {
	// if (s == null) {
	// return 0;
	// }
	// StringBuilder subStr = new StringBuilder("");
	// int maxLength = 0;
	// for (char c : s.toCharArray()) {
	// String thisChar = String.valueOf(c);
	// if (subStr.indexOf(thisChar) > -1) {
	// subStr = new StringBuilder(subStr.substring(subStr.indexOf(thisChar) + 1)).append(thisChar);
	// } else {
	// subStr.append(thisChar);
	// maxLength = Math.max(subStr.length(), maxLength);
	// }
	//
	// }
	// return maxLength;
	// }

	public int lengthOfLongestSubstring(String s) {
		if (s == null) {
			return 0;
		}
		Map<Character, Integer> markMap = new HashMap<>();
		int maxLength = 0;
		int lastIndex = 0;
		char[] chars = s.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (markMap.containsKey(chars[i])) {
				lastIndex = Math.max(lastIndex, markMap.getOrDefault(chars[i], 0));
			}
			markMap.put(chars[i], i);
			maxLength = Math.max(maxLength, i - lastIndex + 1);
		}
		return maxLength;
	}
}
