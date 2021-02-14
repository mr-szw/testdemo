package com.dawei.test.demo.leetcode;

import java.util.Arrays;

import com.google.gson.Gson;

/**
 * 765. 情侣牵手 https://leetcode-cn.com/problems/couples-holding-hands/
 * bilibili https://www.bilibili.com/video/BV13t411v7Fs?from=search&seid=4891757895854352023
 * @author sinbad on 2021/02/14.
 */
public class Question765 {

	public static void main(String[] args) {
		// int[] position = new int[] { 2, 5, 2, 1, 2 };
		int[] position = new int[] { 3, 2, 0, 1 };
		System.out.println(new Gson().toJson(new Question765().minSwapsCouples(position)));
	}
	//
	// public int minSwapsCouples(int[] row) {
	//
	// int count = 0;
	// // 两个人的差值一定得是1 并且不能是 1230 这种
	// for (int i = 0; i < row.length; i = i + 2) {
	// int i1 = row[i];
	// int i2 = row[i + 1];
	// if (Math.abs(i1 - i2) != 1) {
	// count++;
	// }
	// }
	// if (count == 0) {
	// return 0;
	// }
	// return (count) - 1;
	//
	// }

	public int minSwapsCouples(int[] row) {
		int len = row.length;
		UnionFind unionFind = new UnionFind(len / 2);
		for (int i = 0; i < len; i += 2) {
			unionFind.union(row[i] / 2, row[i + 1] / 2);
		}
		return unionFind.getCount();
	}

	class UnionFind {

		private int[] parents;
		private int[] rank;
		private int count;

		public UnionFind(int n) {
			parents = new int[n];
			rank = new int[n];
			Arrays.fill(parents, -1);
			Arrays.fill(rank, 0);
		}

		public int getCount() {
			return count;
		}

		public int findRoot(int i) {
			// 看看i的父节点是不是跟节点
			int pa = i;
			// 证明还有父亲
			while (parents[pa] != -1) {
				// 继续深入
				pa = parents[pa];
			}
			// 返回父亲是根的节点
			return pa;
		}

		// true 需要合并
		// false 不需要做合并
		public boolean union(int x, int y) {
			int rootX = findRoot(x);
			int rootY = findRoot(y);

			// 已经连通了
			if (rootX == rootY) {
				return false;
			}

			// 合并次数
			count++;

			// 选择那一侧进行联通
			if (rank[x] > rank[y]) {
				// x侧高 x做父节点
				parents[rootY] = rootX;
			} else if (rank[x] < rank[y]) {
				parents[rootX] = rootY;
			} else {
				// 高度相同 随便选
				parents[rootX] = rootY;
				// 给接进来的根增加
				rank[rootX]++;
			}
			return true;
		}

	}

}
