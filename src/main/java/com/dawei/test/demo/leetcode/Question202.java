package com.dawei.test.demo.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 202. 快乐数
 * https://leetcode-cn.com/problems/happy-number/
 *
 * @author sinbad on 2020/10/13.
 * 我不快乐
 */
public class Question202 {


	public static void main(String[] args) {

		System.out.println(new Question202().isHappy(2));
	}

	public boolean isHappy(int n) {
		Set<Integer> hasUseSet = new HashSet<>();
		return calculation(n, hasUseSet);
	}


	private boolean calculation(int n, Set<Integer> hasUseSet) {

		if (!hasUseSet.add(n)) {
			return false;
		}
		if (n == 1) {
			return true;
		}
		int sum = 0;
		do {
			int num = n - (n / 10) * 10;
			n = n / 10;
			sum += num * num;
		} while (n > 0);
		return calculation(sum, hasUseSet);
	}

}
