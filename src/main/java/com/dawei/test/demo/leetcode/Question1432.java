package com.dawei.test.demo.leetcode;

import com.google.gson.Gson;

/**
 * 1432. 改变一个整数能得到的最大差值
 * https://leetcode-cn.com/problems/max-difference-you-can-get-from-changing-an-integer/
 *
 * @author sinbad on 2021/1/8.
 */
public class Question1432 {


	public static void main(String[] args) {

		//9909057
		//11
		System.out.println(new Gson().toJson(new Question1432().maxDiff(1101057)));
	}

	public int maxDiff(int num) {
		int max = num;
		int min = num;
		//获取数据的最大值
		String value = String.valueOf(num);
		char[] chars = value.toCharArray();
		char replaceChar = '-';

		//最大值
		for (int i = 0; i < chars.length; i++) {
			replaceChar = chars[i];
			if (replaceChar != '9') {
				break;
			}
		}
		if (replaceChar != '-') {
			String replace = value.replace(replaceChar, '9');
			max = Integer.parseInt(replace);
		}
		//获取最小值

		//将第一位设置为1
		//将第二位设置为0 不同于第一位的第一个

		replaceChar = '-';
		char firstChar = chars[0];
		if (firstChar == '1') {
			for (int i = 1; i < chars.length; i++) {
				if (chars[i] != '1' && chars[i] != '0') {
					replaceChar = chars[i];
					break;
				}
			}
			if (replaceChar != '-') {
				String replaceMin = value.replace(replaceChar, '0');
				min = Integer.parseInt(replaceMin);
			}
		} else {
			String replaceMin = value.replace(firstChar, '1');
			min = Integer.parseInt(replaceMin);
		}
		return max - min;
	}
}
