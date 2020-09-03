package com.dawei.test.demo.leetcode;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 *  最小路径和
 * https://leetcode-cn.com/problems/minimum-path-sum/
 *
 * 动态规划
 * @author sinbad on 2020/08/31.
 */
public class Question64 {


	public static void main(String[] args) {


		String resources = "[[1,3],[3,0,1],[2],[0]]";
		List<List<Integer>>  rooms = new Gson().fromJson(resources, new TypeToken<List<List<Integer>>>() {
		}.getType());

	}

	public int minPathSum(int[][] grid) {

		return 0;
	}
}
