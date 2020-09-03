package com.dawei.test.demo.leetcode;

import java.util.Random;

/**
 * 剑指 Offer 20. 表示数值的字符串
 * https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/
 * @author sinbad on 2020/09/2.
 *
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"都表示数值，但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
 *
 */
public class Question20 {


	public static void main(String[] args) {
		String grid = "1";
		System.out.println(new Question20().isNumber(grid));
	}


	public boolean isNumber(String s) {


		//正负号
		boolean haveSign = false;
		//点号
		boolean havePoint = false;
		boolean haveE = false;
		boolean haveNum = false;

		//String  string = new StringBuffer(s).reverse().toString();
		String string = s;

		char[] chars = string.toCharArray();

		for (char aChar : chars) {
			if (aChar != '.' && aChar != '+' && aChar != '-' && aChar != 'e' && aChar != 'E' && (aChar - '9' > 0 || aChar - '0' <0)) {
				return false;
			}


			if (aChar == '+' || aChar == '-') {
				if (haveSign) {
					return false;
				}
				haveSign = true;
			}
		}
		return false;
	}
}
