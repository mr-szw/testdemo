package com.dawei.test.demo.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/fibonacci-number/
 * 509. 斐波那契数列
 *
 * @author sinbad on 2020/09/15.
 */
public class Question509 {

	public static void main(String[] args) {

		System.out.println(new Question509().fib(2));
		System.out.println(new Question509().fib(3));
		System.out.println(new Question509().fib(4));
		System.out.println("STOP");
	}

	private static Map<Integer, Integer> mapResult = new HashMap<>();
	static {
		mapResult.put(0, 0);
		mapResult.put(1, 1);
		mapResult.put(2, 1);
	}

	public int fib(int n) {
		Integer integer = mapResult.get(n);
		if (integer != null) {
			return integer;
		}

		int thisResult = fib(n - 1) + fib(n - 2);
		mapResult.put(n, thisResult);
		return thisResult;
	}

}
