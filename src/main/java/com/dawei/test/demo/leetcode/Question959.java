package com.dawei.test.demo.leetcode;

import com.google.gson.Gson;

/**
 * 959. 由斜杠划分区域
 * https://leetcode-cn.com/problems/regions-cut-by-slashes/
 *
 * @author sinbad on 2021/1/25.
 * <p>
 */
public class Question959 {


	public static void main(String[] args) {

		int[] position = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
		//int[] position = new int[]{1, 2, 4, 2, 5, 7, 2, 4, 9, 0};
		//System.out.println(new Gson().toJson(new Question959().dailyTemperatures(position)));
	//	System.out.println(new Gson().toJson(new Question959().regionsBySlashes(new String[]{"", "", ""})));
	}



	/**
	 * 表格
	 * <p>
	 * <p>
	 * ====
	 * \ 2 /
	 * 1	 3
	 * / 0 \
	 * ===
	 * <p>
	 * <p>
	 * <p>
	 * <p>
	 * -------------------
	 *
	 * @param grid
	 * @return
	 */
	public int regionsBySlashes(String[] grid) {

		int length = grid.length;

		//每个格子分成四份 作为子模块
		FindUnion findUnion = new FindUnion(length * length * 4);


		for (int i = 0; i < length; i++) {
			char[] chars = grid[i].toCharArray();
			for (int j = 0; j < length; j++) {
				//二维网格转化为一维表格
				int index = (i * length + j) * 4;
				char c = chars[j];

				//单元格内合并
				if (c == '/') {
					//合并左上的图案和右下的图案

					findUnion.union(index, index + 3);
					findUnion.union(index + 1, index + 2);
				} else if (c == '\\') {
					//合并右上 左下
					findUnion.union(index, index + 1);
					findUnion.union(index + 2, index + 3);
				} else {
					//合并所有
					findUnion.union(index, index + 1);
					findUnion.union(index + 1, index + 2);
					findUnion.union(index + 2, index + 3);
				}

				//单元格间合并
				if (j + 1 < length) {
					//跟右侧合并
					findUnion.union(index + 1, 4 * (i * length + j + 1) + 3);
				}
				//跟下面合并
				if (i + 1 < length) {
					findUnion.union(index + 2, 4 * ((i + 1) * length + j));
				}

			}
		}
		return findUnion.getCount();
	}


	class FindUnion {

		private int count;

		private int[] parents;

		public FindUnion(int n) {
			parents = new int[n];
			count = n;
			for (int i = 0; i < parents.length; i++) {
				parents[i] = i;
			}
		}

		public int getCount() {
			return count;
		}

		public int find(int x) {
			while (x != parents[x]) {
				parents[x] = parents[parents[x]];
				x = parents[x];
			}
			return x;
		}

		public void union(int x, int y) {
			int rootX = find(x);
			int rootY = find(y);
			//已经连通
			if (rootX == rootY) {
				return;
			}
			//进行连通
			parents[rootX] = parents[rootY];
			count--;
		}
	}
}