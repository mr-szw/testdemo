package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.google.gson.Gson;

/**
 * 139. 单词拆分 https://leetcode-cn.com/problems/word-break/ 动态规划
 * 动态规划或者回溯
 * 简单回溯超时失败。。。增加记忆法可解
 *  完全背包
 * @author sinbad on 2020/09/11.
 */
public class Question139 {

	public static void main(String[] args) {
		// System.out.println(new Gson().toJson(new Question139().wordBreak("catsandogcat",
		// Arrays.asList("cats", "dog", "sand", "and", "cat", "an"))));
//		System.out.println(new Gson().toJson(new Question139().wordBreak("leetcode",
//				Arrays.asList("leet", "code", "sand", "and", "cat", "an"))));
		System.out.println(new Gson().toJson(new Question139().wordBreak("cars",
				Arrays.asList("car", "ca", "rs"))));
	}

	public boolean wordBreak(String s, List<String> wordDict) {

		// 标记字典表数据
		Map<Character, List<String>> markHeadMap = new HashMap<>();
		// 记忆化
		int[] memoryPath = new int[s.length() + 1];
		for (String word : wordDict) {
			char workHead = word.charAt(0);
			List<String> orDefault = markHeadMap.getOrDefault(workHead, new ArrayList<>());
			orDefault.add(word);
			markHeadMap.put(workHead, orDefault);
		}
		return wordBreakCheck(0, s, markHeadMap, memoryPath);
	}

	public boolean wordBreakCheck(int index, String originalStr,
			Map<Character, List<String>> markHeadMap, int[] memoryPath) {
		if (index == originalStr.length()) {
			return true;
		}
		if (memoryPath[index] != 0) {
			return memoryPath[index] == 1;
		}
		String thisWork = originalStr.substring(index);
		char charAt = originalStr.charAt(index);
		List<String> canCheckList = markHeadMap.get(charAt);
		if (canCheckList == null) {
			return false;
		}

		for (String checkWork : canCheckList) {
			int i = thisWork.indexOf(checkWork);
			// 是首位
			if (i == 0) {
				boolean breakCheck = wordBreakCheck(index + checkWork.length() , originalStr, markHeadMap, memoryPath);
				if (breakCheck) {
					memoryPath[index] = 1;
					return true;
				}
			}
		}
		memoryPath[index] = -1;
		return false;
	}
}
