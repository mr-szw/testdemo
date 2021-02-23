package com.dawei.test.demo.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * https://leetcode-cn.com/problems/rotate-image/
 * 48. 旋转图像
 *
 * @author sinbad on 2020/12/19.
 */
public class Question692 {


	public static void main(String[] args) {


	}

	public List<String> topKFrequent(String[] words, int k) {
		Map<String, Integer> wordNumMap = new HashMap<>();
		for (String word : words) {
			wordNumMap.put(word, wordNumMap.getOrDefault(word, 0) + 1);
		}
		//全排序
		List<String> collect =
				wordNumMap.keySet().stream().sorted((item1, item2) -> wordNumMap.get(item1).equals(wordNumMap.get(item2))
						? item1.compareTo(item2) : wordNumMap.get(item1) - wordNumMap.get(item2)).collect(Collectors.toList());
		return collect.subList(0, k);
	}


}
