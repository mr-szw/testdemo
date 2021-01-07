package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.google.gson.Gson;

/**
 * 834. 树中距离之和
 * https://leetcode-cn.com/problems/sum-of-distances-in-tree/
 *
 * @author sinbad on 2020/10/08.
 *
 * TODO dp问题 暂时不会啊
 */
public class Question834 {


	public static void main(String[] args) {

		int[][] edges = new int[][]{{0,1}, {0, 2}, {2,3}, {2,4}, {2,5}};

		new Question834().sumOfDistancesInTree(6, edges);
	}


	public int[] sumOfDistancesInTree(int N, int[][] edges) {

		int[] dp = new int[N];
		//记录每个点的可达目的地
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < N; ++i) {
			graph.add(new ArrayList<>());
		}
		for (int[] edge : edges) {
			int u = edge[0];
			int v = edge[1];
			graph.get(u).add(v);
			graph.get(v).add(u);
		}


		int[] ans = new int[N];
		int[] sz = new int[N];

		dfs(0, -1, sz, dp, graph);
		dfs2(0, -1, ans, sz, dp, graph);
		return ans;
	}

	public void dfs(int from, int to, int[] sz, int[] dp, List<List<Integer>> graph) {
		sz[from] = 1;
		dp[from] = 0;
		for (int v : graph.get(from)) {
			if (v == to) {
				continue;
			}
			dfs(v, from, sz, dp, graph);
			dp[from] += dp[v] + sz[v];
			sz[from] += sz[v];
		}
	}

	public void dfs2(int u, int f, int[] ans, int[] sz, int[] dp, List<List<Integer>> graph) {
		ans[u] = dp[u];
		for (int v : graph.get(u)) {
			if (v == f) {
				continue;
			}
			int pu = dp[u], pv = dp[v];
			int su = sz[u], sv = sz[v];

			dp[u] -= dp[v] + sz[v];
			sz[u] -= sz[v];
			dp[v] += dp[u] + sz[u];
			sz[v] += sz[u];

			dfs2(v, u, ans, sz, dp, graph);

			dp[u] = pu;
			dp[v] = pv;
			sz[u] = su;
			sz[v] = sv;
		}
	}

}