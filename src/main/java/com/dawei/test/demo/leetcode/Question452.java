package com.dawei.test.demo.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 最小路径和
 * https://leetcode-cn.com/problems/minimum-path-sum/
 * <p>
 * 动态规划
 *
 * @author sinbad on 2020/11/23.
 */
public class Question452 {


	public static void main(String[] args) {

		System.out.println(new Question452().findMinArrowShots(new int[][]{}));
	}

	public int findMinArrowShots(int[][] points) {
		if (points == null || points.length == 0) {
			return 0;
		}
		if (points.length == 1) {
			return 1;
		}

		Arrays.sort(points, (item1, item2) ->
				item1[1] > item2[1] ? 1 : -1);
		int counter = 1;
		int firstRight = points[0][1];
		for (int i = 1; i < points.length; i++) {
			int pointLeft = points[i][0];
			if (pointLeft > firstRight) {
				firstRight = points[i][1];
				counter++;
			}
		}
		return counter;
	}
}
