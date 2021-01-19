package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 684. 冗余连接
 * https://leetcode-cn.com/problems/redundant-connection/ *
 *
 * @author sinbad on 2021/1/13.
 */
public class Question684 {


	public static void main(String[] args) {

		System.out.println(Arrays.toString(new Question684().findRedundantConnection(new int[][]{{1, 3, 4, 12}})));

	}

	public int[] findRedundantConnection(int[][] edges) {


		int length = edges.length;

		boolean[] visited = new boolean[length];

		Map<Integer, List<Integer>> edgeListMap = new HashMap<>();

		for (int[] edge : edges) {
			List<Integer> itemList = edgeListMap.getOrDefault(edge[0], new ArrayList<>());
			itemList.add(edge[1]);
			edgeListMap.put(edge[0], itemList);
		}
		for (int i = 0; i < length; i++) {


			visited[i] = true;
			List<Integer> itemList = edgeListMap.get(i + 1);

		}
		return new int[]{};
	}




}
