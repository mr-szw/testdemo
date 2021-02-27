package com.dawei.test.demo.leetcode;

/**
 * 1579. 保证图可完全遍历
 * https://leetcode-cn.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/
 *
 * @author sinbad on 2020/08/19.
 */
public class Question1579 {

	public static void main(String[] args) {
		System.out.println(new Question1579().maxNumEdgesToRemove(3, new int[][] {}));
	}

	public int maxNumEdgesToRemove(int n, int[][] edges) {

		int result = 0;
		UnionFind unionFindAlice = new UnionFind(n);
		UnionFind unionFindBob = new UnionFind(n);
		for (int[] edge : edges) {
			int type = edge[0];
			int path1 = edge[1] - 1;
			int path2 = edge[2] - 1;
			if (type == 3) {
				if (!unionFindAlice.union(path1, path2)) {
					result++;
				} else {
					unionFindBob.union(path1, path2);
				}
			} else if (type == 2) {
				if (!unionFindBob.union(path1, path2)) {
					result++;
				}

			} else if (type == 1) {
				if (!unionFindAlice.union(path1, path2)) {
					result++;
				}
			}
		}

		if (unionFindAlice.setCount != 1 || unionFindBob.setCount != 1) {
			return -1;
		}
		return result;
	}

	class UnionFind {

		int count;

		int[] parents;

		// 当前连通分量数目
		int setCount;

		public UnionFind(int n) {
			this.count = n;
			this.setCount = n;
			parents = new int[n];
			for (int i = 0; i < n; i++) {
				parents[i] = i;
			}
		}

		public int findRoot(int x) {
			if (x != parents[x]) {
				parents[x] = findRoot(parents[x]);

			}
			return parents[x];
		}

		public boolean union(int x, int y) {
			// 已经联通
			if (connected(x, y)) {
				return false;
			} else {
				parents[y] = x;
				this.setCount--;
				return true;
			}
		}

		public boolean connected(int x, int y) {
			return findRoot(x) == findRoot(y);
		}

	}
}
