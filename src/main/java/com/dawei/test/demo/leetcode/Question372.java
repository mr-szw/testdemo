package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 超级次方
 * https://leetcode-cn.com/problems/super-pow/
 *
 * @author sinbad on 2020/08/24.
 */
public class Question372 {


	public static void main(String[] args) {
		List<Integer> integerList = new ArrayList<>();
		Random random = new Random();
		int len = random.nextInt(5);
		for (int i = 0; i < len; i++) {
			integerList.add(random.nextInt(10));
		}
		//integerList.forEach(System.out::print);


		integerList.clear();

		char[] chars = "2147483647".toCharArray();
		for (char aChar : chars) {
			integerList.add((int) aChar - 48);
		}

		integerList.forEach(System.out::print);
		Integer[] integers = integerList.toArray(new Integer[0]);


		int a = random.nextInt(9) + 1;
		a = 200;
		System.out.println();
		System.out.println(a);

		System.out.println(new Question372().superPow(a, integers));

	}

	//b =[1,5,6,7]
	// a ^ 1567
	// =< a ^ 1560 * a ^ 7
	// =< (a ^ 156) ^ 10 * a ^ 7


	public int superPow(int a, Integer[] b) {

		int length = b.length;

		mySuperPow(a, b);


	}

	public double mySuperPow(int a, Integer[] b) {

		double pow = Math.pow(a, b[b.length - 1]);
		return pow % 1337 + Math.pow(mySuperPow(a, Arrays.copyOf(b, b.length - 2)), 10);
	}


}
