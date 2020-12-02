package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;

/**
 * 1370. 上升下降字符串 https://leetcode-cn.com/problems/increasing-decreasing-string/
 *
 * @author sinbad on 2020/11/225.
 */
public class Question1370 {

	public static void main(String[] args) {

		System.out.println(new Gson().toJson(new Question1370().sortString("leetcode")));
	}

	public String sortString(String s) {

		StringBuilder result = new StringBuilder();
		char[] chars = s.toCharArray();
		int[] charMark = new int[26];
		int length = s.length();
		for (char c : chars) {
			int charNum = c - 'a';
			charMark[charNum]++;
		}
		while (length > result.length()) {
			for (int i = 0; i < 26; i++) {
				if (charMark[i] > 0) {
					result.append((char) (i + 'a'));
					charMark[i]--;
				}
			}
			for (int i = 25; i >= 0; i--) {
				if (charMark[i] > 0) {
					result.append((char) (i + 'a'));
					charMark[i]--;
				}
			}
		}
		return result.toString();
	}

	public String sortStringMan(String s) {

		StringBuilder result = new StringBuilder();
		Map<Integer, List<Integer>> charIndexMap = new HashMap<>();
		char[] chars = s.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			List<Integer> orDefault = charIndexMap.getOrDefault(chars[i] - 'a', new ArrayList<>());
			orDefault.add(i);
			charIndexMap.put(chars[i] - 'a', orDefault);
		}

		List<Integer> collect = charIndexMap.keySet().stream().sorted(Collections.reverseOrder())
				.collect(Collectors.toList());
		Iterator<Integer> iterator;
		boolean hasNext = true;
		while (hasNext) {
			hasNext = false;
			Collections.reverse(collect);
			iterator = collect.iterator();
			while (iterator.hasNext()) {
				Integer charValue = iterator.next();
				List<Integer> charIndexList = charIndexMap.get(charValue);
				if (charIndexList == null) {
					continue;
				}
				hasNext = true;
				result.append((char) (charValue + 'a'));
				charIndexList.remove(0);
				if (charIndexList.size() == 0) {
					charIndexMap.put(charValue, null);
				} else {
					charIndexMap.put(charValue, charIndexList);
				}
			}
		}
		return result.toString();
	}
}
