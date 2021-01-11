package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.checkerframework.checker.units.qual.A;

import com.dawei.test.demo.utils.GsonUtil;

/**
 * https://leetcode-cn.com/problems/word-ladder/
 * 127. 单词接龙
 *
 * @author sinbad on 2021/1/11.
 */
public class Question127 {


	public static void main(String[] args) {

		System.out.println(GsonUtil.toJson(new Question127().ladderLength("", "", new ArrayList<>())));

	}

	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		int beginLength = beginWord.length();
		int endLength = endWord.length();

		Map<Integer, List<Character>> wordCharListMap = new HashMap<>(beginLength);

		//标记所有值
		for (String word : wordList) {
			int length = word.length();
			for (int i = 0; i < length; i++) {
				List<Character> orDefault = wordCharListMap.getOrDefault(i, new ArrayList<>());
				orDefault.add(word.charAt(i));
				wordCharListMap.put(i, orDefault);
			}
		}

		//判断end内容时候包含在字典中
		for (int i = 0; i < endLength; i++) {
			List<Character> characterList = wordCharListMap.get(i);
			if (!characterList.contains(endWord.charAt(i))) {
				return 0;
			}
		}

		//找到他的前一个可能的内容 构建图
		for (int i = 0; i < endLength; i++) {

		}



		return 1;
	}


}
