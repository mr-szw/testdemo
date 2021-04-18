package com.dawei.test.demo.leetcode;


import com.google.gson.Gson;

/**
 * 520. 检测大写字母
 * https://leetcode-cn.com/problems/detect-capital/
 *
 * @author sinbad on 2020/11/22.
 * <p>
 */
public class Question520 {


	public static void main(String[] args) {

		int[] prices = new int[]{};

		System.out.println(new Gson().toJson(new Question520().detectCapitalUse("FlaF")));
	}

	public boolean detectCapitalUse(String word) {
		char[] chars = word.toCharArray();
		if (chars.length <= 1) {
			return true;
		}
		//首字母大写
		boolean firstBig = (chars[0] - 'a') < 0;
		boolean next = false;
		for (int i = 1; i < chars.length; i++) {
			//首字母大写 后边只能全是大写或者小写
			//首字母小写 后面必须小写
			if (firstBig) {
				if (i == 1) {
					next = (chars[i] - 'a') < 0;
				} else {
					if (next != chars[i] - 'a' < 0) {
						return false;
					}
				}
			} else {
				if (chars[i] - 'a' < 0) {
					return false;
				}
			}
		}
		return true;

	}

}

