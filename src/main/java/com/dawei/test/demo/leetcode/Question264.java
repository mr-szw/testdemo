package com.dawei.test.demo.leetcode;

/**
 * https://leetcode-cn.com/problems/ugly-number-ii/
 * <p>
 * 丑数 II
 *
 * @author sinbad on 2020/08/30.
 */
public class Question264 {


	// 1, 2, 3, 4, 5, 6, 8, 9, 10, 12

	public static void main(String[] args) {


		System.out.println(new Question264().nthUglyNumber(10));
	}


	public int nthUglyNumber(int n) {


		if (n == 1) {
			return 1;
		}

		int index = 1;
		//System.out.println(index + "     1");
		int num = 1;
		//求这个数是否是质因数
		do {
			num++;

			if (checkUglyNumber(num)) {
				index++;
				//System.out.println(index + "    " + num);
			}

		} while (index < n);
		return num;
	}


	//分解质因数


	boolean checkUglyNumber(int num) {
		if (num % 2 != 0 && num % 3 != 0 && num % 5 != 0) {
			return false;
		}

		for (int x = 2; x <= num; x++) {
			if (num % x == 0) {
				num /= x;
				if (x != 2 && x != 3 && x != 5) {
					return false;
				}
				// 为了防止该整数有多个相同质因数最终只能输出一个的情况
				x--;
			}
		}
		return true;
	}



	//1】1

	// 2 * 1  3 * 1 5 * 1

	//2】 1. 2

	// 2* 2 3 * 1 5 * 1

	//3】 1 . 2 3

	// 2 * 2 3 * 2 5 * 1

	//3】 1 . 2 。 3 . 4

	// 2 * 3  3 * 2  5 * 1

	// 4】  1 . 2 。 3 . 4 . 5

	// 2 * 3  3 * 2  5 * 2

	// 5】  1 . 2 。 3 . 4 . 5 . 6

	// 2 * 4  3 * 3  5 * 2
	
	// 5】  1 . 2 。 3 . 4 . 5 . 6 . 8


	//高级的 动态规划
//	public int nthUglyNumber(int n) {
//		int[] dp = new int[n];
//		dp[0] = 1;
//		int i2 = 0, i3 = 0, i5 = 0;
//		for (int i = 1; i < n; i++) {
//			int min = Math.min(dp[i2] * 2, Math.min(dp[i3] * 3, dp[i5] * 5));
//			if (min == dp[i2] * 2) i2++;
//			if (min == dp[i3] * 3) i3++;
//			if (min == dp[i5] * 5) i5++;
//			dp[i] = min;
//		}
//
//		return dp[n - 1];
//	}


}
