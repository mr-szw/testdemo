package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 830. 较大分组的位置
 * https://leetcode-cn.com/problems/positions-of-large-groups/
 *
 * @author sinbad on 2021/1/5.
 */
public class Question830 {

	public static void main(String[] args) {
		//String grid = "abcdddeeeeaabbbcd";
		String grid = "aaa";
		//String grid = "aa";
		//String grid = "abbxxxxzzy";
		System.out.println(new Question830().largeGroupPositions(grid));
	}

	public List<List<Integer>> largeGroupPositions(String s) {

		List<List<Integer>> resultList = new ArrayList<>();
		char[] chars = s.toCharArray();

		int start = 0;
		char last = 0;

		for (int i = 0; i < chars.length; i++) {

			if (chars[i] != last) {
				last = chars[i];
				if (i - start > 2) {
					resultList.add(Arrays.asList(start, i - 1));
				}
				start = i;
			}
		}
		if (chars.length - start >= 3) {
			resultList.add(Arrays.asList(start, chars.length - 1));
		}
		return resultList;
	}

}
