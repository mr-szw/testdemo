package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

import com.google.gson.Gson;

/**
 * 135. 分发糖果
 * https://leetcode-cn.com/problems/candy/
 *
 * @author sinbad on 2020/12/24.
 */
public class Question135 {


	public static void main(String[] args) {

		int[] position = new int[]{1, 3, 2, 2, 1};
		System.out.println(new Gson().toJson(new Question135().candy(position)));
	}

	public int candy(int[] ratings) {


		int length = ratings.length;

		int[] candyAry = new int[length];
		for (int i = 1; i < ratings.length; i++) {
			if (ratings[i] > ratings[i - 1]) {
				candyAry[i] = candyAry[i - 1] + 1;
			}
		}
		for (int i = length - 1; i > 0; i--) {
			if (ratings[i] < ratings[i - 1]) {
				if (candyAry[i -1] > candyAry[i]) {
					continue;
				}
				candyAry[i - 1] = Math.max(candyAry[i - 1], candyAry[i]) + 1;
			}
		}
		return Arrays.stream(candyAry).sum() + length;
	}
}
