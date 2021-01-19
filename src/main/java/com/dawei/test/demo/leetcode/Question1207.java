package com.dawei.test.demo.leetcode;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.Gson;

/**
 * 1207. 独一无二的出现次数 https://leetcode-cn.com/problems/unique-number-of-occurrences/
 *
 * @author sinbad on 2020/10/28.
 */
public class Question1207 {

	public static void main(String[] args) {

		System.out.println(new Gson().toJson(new

		Question1207().

				uniqueOccurrences(new int[] { 1, 2, 2, 1, 1, 3 })));

	}

	public boolean uniqueOccurrences(int[] arr) {
		int[] markAry = new int[20002];
		int[] needCheck = new int[20002];
		Set<Integer> checkSet = new HashSet<>();
		for (int i : arr) {
			markAry[i + 1000] += 1;
		}
		for (int i : arr) {
			if (needCheck[i + 1000] == 0) {
				if (!checkSet.add(markAry[i + 1000])) {
					return false;
				}
				needCheck[i + 1000] = 1;
			}
		}
		return true;
	}
}