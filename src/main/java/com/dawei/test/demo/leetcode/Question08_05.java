package com.dawei.test.demo.leetcode;


import com.google.gson.Gson;

/**
 * 递归乘法
 * https://leetcode-cn.com/problems/recursive-mulitply-lcci/ *
 *
 * @author sinbad on 2021/1/11.
 */
public class Question08_05 {


	public static void main(String[] args) {


		//System.out.println(new Gson().toJson(new Question08_05().multiply(73807517, 14)));
		System.out.println(new Gson().toJson(new Question08_05().multiply0(3, 4)));
	}

	public int multiply(int A, int B) {

		if (A == 0) {
			return 0;
		}
		//A应该是小数
		if (A > B) {
			return multiply(B, A);
		}

		//偶数
		if ((A & 1) == 0) {
			return (B << 1) + multiply(A - 2, B);
		} else { //奇数
			return B + multiply(A - 1, B);
		}
	}


	public int multiply0(int A, int B) {

		//将所有数据转换成 2 倍的方式
		int min = Math.min(A, B);
		int max = Math.max(A, B);

		if (min == 0) {
			return 0;
		}
		int result = 0;
		while (min != 0) {
			if (min % 2 == 0) {
				result += (max << 1);
				if (min == 2) {
					min = 0;
				} else {
					min = min - 2;
				}
			} else {
				min = min - 1;
				result += max;
			}
		}
		return result;
	}

}

