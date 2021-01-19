package com.dawei.test.demo.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/
 * 37. 解数独
 *
 * @author sinbad on 2020/09/15.
 */
public class Question62 {

	public static void main(String[] args) {

		new Question62().lastRemaining(1, 2);
		System.out.println("STOP");
	}

	public int lastRemaining(int n, int m) {

		int pos = 0; // 最终活下来那个人的初始位置
		for(int i = 2; i <= n; i++){
			pos = (pos + m) % i;  // 每次循环右移
		}
		return pos;
	}
}
