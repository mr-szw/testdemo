package com.dawei.test.demo.leetcode;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 *  344 反转字符串
 https://leetcode-cn.com/problems/reverse-string/ *
 * 动态规划
 * @author sinbad on 2020/10/08.
 */
public class Question344 {


	public static void main(String[] args) {


	}

	public void reverseString(char[] s) {
		int left = 0;
		int right = s.length -1;
		while (left < right) {
			char temp = s[left];
			s[left] = s[right];
			s[right] = temp;
			left++;
			right--;
		}
	}
}
