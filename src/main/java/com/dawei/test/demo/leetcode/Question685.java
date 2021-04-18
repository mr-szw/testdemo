package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 685. 冗余连接 II
 * https://leetcode-cn.com/problems/redundant-connection-ii/
 * 没完成
 *
 * @author sinbad on 2020/08/31.
 */
public class Question685 {


	public static void main(String[] args) {


		String resources = "[[2,1],[3,1],[4,2],[1,4]]";
		int[][] rooms = new Gson().fromJson(resources, new TypeToken<int[][]>() {
		}.getType());

		System.out.println(new Question685().findRedundantDirectedConnection(rooms));

	}

	public int[] findRedundantDirectedConnection(int[][] edges) {

		List<Integer> youNeedCheckFather = new ArrayList<>();
		int[] buildRingResult = new int[2];
		int[] twoFatherResult = new int[2];

		Map<Integer, List<Integer>> yourChildMap = new HashMap<>();
		Map<Integer, List<Integer>> yourFatherMap = new HashMap<>();
		for (int[] edge : edges) {
			int father = edge[0];
			int child = edge[1];
			List<Integer> nextNodeList = yourChildMap.get(father);

			if (nextNodeList == null) {
				nextNodeList = new ArrayList<>();
			} else {
				//证明两个子节点 多半他是根了
			}

			//2、构成环
			boolean buildRing = checkBuildRing(yourChildMap, father, child);
			if (buildRing) {
				buildRingResult = new int[]{father, child};
			}

			nextNodeList.add(child);
			yourChildMap.put(father, nextNodeList);

			List<Integer> fatherNodeList = yourFatherMap.get(child);
			if (fatherNodeList == null) {
				fatherNodeList = new ArrayList<>();
			} else {
				//证明有两个爹 需要去爹 	//1、有两个父节点
				youNeedCheckFather.add(child);
				twoFatherResult = new int[]{father, child};
			}
			fatherNodeList.add(father);
			yourFatherMap.put(child, fatherNodeList);

		}

		if (buildRingResult[0] == 0) {
			buildRingResult = twoFatherResult;
		}
		return buildRingResult;

	}


	private boolean checkBuildRing(Map<Integer, List<Integer>> yourChildMap, int you, int yourChild) {
		List<Integer> youChildList = yourChildMap.get(yourChild);
		if (youChildList == null) {
			return false;
		}
		for (Integer child : youChildList) {
			if (child != you) {
				boolean buildRing = checkBuildRing(yourChildMap, you, child);
				if (buildRing) {
					return true;
				}
			} else {
				return true;
			}

		}
		return false;
	}


}
