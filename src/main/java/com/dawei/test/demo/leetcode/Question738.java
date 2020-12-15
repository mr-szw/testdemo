package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import com.google.gson.Gson;

/**
 * 738. 单调递增的数字
 * https://leetcode-cn.com/problems/monotone-increasing-digits/
 *
 * @author sinbad on 2020/12/15.
 */
public class Question738 {


	public static void main(String[] args) {
		System.out.println(new Gson().toJson(new Question738().monotoneIncreasingDigits(332)));
	}

	public int monotoneIncreasingDigits(int N) {
		if (N < 10) {
			return N;
		}
		char[] chars = String.valueOf(N).toCharArray();

		int length = chars.length;
		int index = 0;
		for (int i = 0; i < length - 1; ) {
			if (chars[i] <= chars[i + 1]) {
				i++;
				index++;
			} else {
				chars[index] = (char) (chars[index] - 1);
				for (int j = index + 1; j < length; j++) {
					chars[j] = '9';
				}
				i =0;
				index = 0;
			}
		}
		return Integer.parseInt(new String(chars));
	}

}
