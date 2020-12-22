package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 842. 将数组拆分成斐波那契序列 https://leetcode-cn.com/problems/split-array-into-fibonacci-sequence/
 *
 * @author sinbad on 2020/12/08.
 */
public class Question842 {

	public static void main(String[] args) {

		new Question842().splitIntoFibonacci("11235");
	}

	public List<Integer> splitIntoFibonacci(String S) {

		List<Integer> tempResultList =new ArrayList<>();
		int length = S.length();

		int step = 1;
		long first = 0;
		long second = 0;

		for (int i = 0; i < length - 2; i = i + step) {

			first = S.charAt(i) - '0';
			second = S.charAt(i + 1) - '0';
			long three = S.charAt(i + 1) - '0';
			if (first + second != three) {
				break;
			}

		}


		return Collections.emptyList();
	}
}
