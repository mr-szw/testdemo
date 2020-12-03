package com.dawei.test.demo.leetcode;

import java.util.Arrays;

/**
 * 204. 计数质数
 * https://leetcode-cn.com/problems/count-primes/
 *
 * @author sinbad on 2020/11/24.
 */
public class Question204 {


	public static void main(String[] args) {
		System.out.println(new Question204().countPrimes(10));
	}


	public int countPrimesBaoli(int n) {
		int count = 0;


		for (int i = 2; i < n; i++) {
			boolean flag = true;
			int index = 2;
			while (index < i) {
				if (i % index == 0) {
					flag = false;
					break;
				}
				index++;
			}
			if (flag) {
				count++;
			}
		}

		return count;

	}


	public int countPrimes(int n) {
		//啥啥啥算法 要是你不是然后你乘完的别的数不是质数
		//高效 2 * 1 /* 2 /* 3 /* 4 /* 5 一下子覆盖挺多
		boolean[] markAry = new boolean[n];
		Arrays.fill(markAry, true);
		int count = 0;
		for (int i = 2; i * i < n; i++) {
			if (markAry[i]) {
				for (int j = i * i; j < n; j = i + j) {
					markAry[j] = false;
				}
			}
		}
		for (int i = n - 1; i >= 2; i--) {
			if (markAry[i]) {
				count++;
			}
		}
		return count;
	}
}
