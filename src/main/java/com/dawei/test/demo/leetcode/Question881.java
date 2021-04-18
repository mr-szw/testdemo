package com.dawei.test.demo.leetcode;

import java.util.Arrays;

import com.dawei.test.demo.utils.GsonUtil;

/**
 * https://leetcode-cn.com/problems/boats-to-save-people/
 * 881. 救生艇
 *
 * @author sinbad on 2021/1/20.
 */
public class Question881 {


	public static void main(String[] args) {

		System.out.println(new Question881().numRescueBoats(new int[]{1, 2}, 10));

	}
	public int numRescueBoats(int[] people, int limit) {
		int result =0;
		int length = people.length;
		int min = 0;
		int max = length - 1;

		Arrays.sort(people);

		while (min < max) {
			if (people[min] + people[max] < limit) {
				min++;
			}
			result++;
			max--;
		}
		if (max == min) {
			result++;
		}

		return result;
	}
}
