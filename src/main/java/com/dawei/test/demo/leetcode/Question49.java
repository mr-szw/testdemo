package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;

/**
 * 49. 字母异位词分组
 * https://leetcode-cn.com/problems/group-anagrams/
 *
 * @author sinbad on 2020/12/14.
 */
public class Question49 {


	public static void main(String[] args) {
		int[] ints = {};
		System.out.println(new Gson().toJson(new Question49().groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"})));
	}

	public List<List<String>> groupAnagrams(String[] strs) {

		//首先 将各个字符串 内部的字母做排序 得到新的str
		//然后使用一个map 存储数据

		Map<String, List<String>> dateMap = Stream.of(strs).collect(Collectors.groupingBy(str -> {
			char[] chars = str.toCharArray();
			Arrays.sort(chars);
			return new String(chars);
		}));


		return new ArrayList<>(dateMap.values());
	}
}
