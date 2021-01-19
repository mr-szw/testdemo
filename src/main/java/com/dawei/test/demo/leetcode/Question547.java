package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 547. 省份数量 https://leetcode-cn.com/problems/number-of-provinces/
 * 
 * @author sinbad on 2021/1/7.
 *
 */
public class Question547 {

	public static void main(String[] args) {
		System.out.println(new Question547().findCircleNum(new int[][] {}));
	}

	public int findCircleNum(int[][] isConnected) {

		// 暴力 找出所有相连的城市
		// 或者找出所有能由1连起来的点
		// 剪枝： 通过单侧判断即可

		// int[][] isConnected 是无向图的邻接矩阵，n 为无向图的顶点数量
		int length = isConnected.length;
		// 定义 boolean 数组标识顶点是否被访问
		boolean[] visited = new boolean[length];

		// 定义 count 来累计遍历过的连通域的数量
		int count = 0;
		Queue<Integer> queue = new LinkedList<>();
		//记录每个省会有哪些连通集
		List<Set<Integer>> combinationList = new ArrayList<>();
		for (int i = 0; i < length; i++) {
			// 若当前节点被访问过，说明已经存在一个联通集
			if (visited[i]) {
				continue;
			}
			count++;
			Set<Integer> thisCombinationSet = new HashSet<>();
			thisCombinationSet.add(i);
			visited[i] = true;
			queue.offer(i);

			for (int weight = i + 1 ; weight < length; weight++) {
				if (isConnected[i][weight] == 1) {
					thisCombinationSet.add(weight);
					visited[i] = true;
				}
			}

			while (!queue.isEmpty()) {
				int v = queue.poll();
				for (int w = 0; w < length; w++) {
					if (isConnected[v][w] == 1 && !visited[w]) {
						visited[w] = true;
						queue.offer(w);
					}
				}
			}
		}
		return count;
	}
}
