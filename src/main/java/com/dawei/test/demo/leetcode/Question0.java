package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.checkerframework.checker.units.qual.A;

/**
 * 学习
 * 两点之前所有的路径
 */
public class Question0 {

	public static void main(String[] args) {

		new Question0().path(1, 6, new int[][]{
				{1, 2}, {1, 3}, {1, 4},
				{2, 3}, {2, 5},
				{3, 4}, {3, 6},
				{4, 7},
				{5, 6},
				{6, 7},
		});

	}

	public List<List<Integer>> path(int from, int to, int[][] edges) {
//可通达的边

		Set<Integer> pointSet = new HashSet<>();
		for (int[] edge : edges) {
			int point1 = edge[0];
			int point2 = edge[1];
			pointSet.add(point1);
			pointSet.add(point2);
		}
		int pointNum = pointSet.size();
		List<List<Integer>> connectedEdge = new ArrayList<>(pointNum);
		for (int i = 0; i <= pointNum; i++) {
			connectedEdge.add(new ArrayList<>());
		}
		for (int[] edge : edges) {
			int point1 = edge[0];
			int point2 = edge[1];
			if (connectedEdge.get(point1) == null) {
				connectedEdge.set(point1, new ArrayList<>());
			}
			if (connectedEdge.get(point2) == null) {
				connectedEdge.set(point2, new ArrayList<>());
			}
			connectedEdge.get(point1).add(point2);
			connectedEdge.get(point2).add(point1);
		}


		Queue<Integer> path = new LinkedList<>();
		Map<Integer, Boolean> visitedMap = new HashMap<>();
		List<List<Integer>> resultPathList = new ArrayList<>();
		return getPath(from, to, connectedEdge, path,visitedMap, resultPathList);
	}


	public List<List<Integer>> getPath(int from, int to, List<List<Integer>> connectedEdge, Queue<Integer> path, Map<Integer, Boolean> visitedMap, List<List<Integer>> resultPathList) {

		path.add(from);
		if (from == to) {
			resultPathList.add(new ArrayList<>(path));
			path.poll();
		}
		if (visitedMap.getOrDefault(from, false)) {
			path.poll();
			getPath(from, to, connectedEdge, path, visitedMap, resultPathList);
		} else {
			visitedMap.put(from, true);
		}
		List<Integer> pointList = connectedEdge.get(from);
		for (Integer point : pointList) {
			getPath(point, to, connectedEdge, path, visitedMap, resultPathList);
		}
		return null;
	}


}

