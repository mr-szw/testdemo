package com.dawei.test.demo.leetcode;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 684. 冗余连接
 * https://leetcode-cn.com/problems/redundant-connection/ *
=======
import java.util.Arrays;

/**
 * 684. 冗余连接 https://leetcode-cn.com/problems/redundant-connection/
>>>>>>> c452d0b03dfde8d78cba8a0f62b1f49eef1351f3
 *
 * @author sinbad on 2021/1/13.
 */
public class Question684 {

<<<<<<< HEAD

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




=======
	public static void main(String[] args) {
		System.out.println(Arrays.toString(new Question684().findRedundantConnection(
				new int[][] { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 1, 4 }, { 1, 5 } })));
	}

	// 连通器 记录每个数据能够到达的最终位置 通过判断多个数据的最终位置判断是否构成串联
	public int[] findRedundantConnection(int[][] edges) {
		int nodesCount = edges.length;
		int[] parent = new int[nodesCount + 1];
		for (int i = 1; i <= nodesCount; i++) {
			parent[i] = i;
		}
		for (int[] edge : edges) {
			int node1 = edge[0];

			int node2 = edge[1];
			// 是否属于同一连通量
			int unionLink1 = findLastLeaf(parent, node1);
			int unionLink2 = findLastLeaf(parent, node2);
			if (unionLink1 != unionLink2) {
				// 增加到连通量中
				addNode(parent, unionLink1, unionLink2);
			} else {
				return edge;
			}
		}
		return new int[0];
	}

	public void addNode(int[] parent, int unionLink1, int unionLink2) {
		// 找第一个节点属于哪个连通量
		parent[unionLink1] = unionLink2;
	}

	// 如果过这个数字没有跟别人连通，则直接返回，否则找到其根连通器
	public int findLastLeaf(int[] parent, int index) {
		int unionFlag = parent[index];
		if (unionFlag != index) {
			unionFlag = findLastLeaf(parent, unionFlag);
			parent[index] = unionFlag;
		}
		return unionFlag;
	}

>>>>>>> c452d0b03dfde8d78cba8a0f62b1f49eef1351f3
}
