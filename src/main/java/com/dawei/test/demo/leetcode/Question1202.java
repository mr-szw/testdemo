//package com.dawei.test.demo.leetcode;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.PriorityQueue;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import org.checkerframework.checker.units.qual.A;
//
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//
///**
// * 1202. 交换字符串中的元素 https://leetcode-cn.com/problems/smallest-string-with-swaps/
// *
// * 动态规划
// *
// * @author sinbad on 2021/1/11.
// */
//public class Question1202 {
//
//	public static void main(String[] args) {
//
//		String s = new Question1202().smallestStringWithSwaps("", new ArrayList<>());
//		System.out.println(s);
//
//	}
//
//	public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
//		int length = s.length();
//		Map<Character, Set<Character>> pathMap = new HashMap<>(length);
//		// 获取每个字符可以到达的字符位置
//		for (List<Integer> pair : pairs) {
//			char key = s.charAt(pair.get(0));
//			Set<Character> orDefault = pathMap.getOrDefault(key, new HashSet<>());
//			orDefault.add(s.charAt(pair.get(1)));
//			pathMap.put(key, orDefault);
//		}
//		Set<Character> characters1 = pathMap.keySet();
//		Map<Integer, List<Character>> pathOrderMap = new HashMap<>();
//		// 排序
//		for (Integer start : pathKeySet) {
//			Set<Character> characters = pathMap.get(start);
//			if (characters.size() > 1) {
//				List<Character> collect = characters.stream().sorted().collect(Collectors.toList());
//				pathOrderMap.put(start, collect);
//			} else {
//				pathOrderMap.put(start, new ArrayList<>(characters));
//			}
//		}
//
//		for (int i = length - 1; i >= 0; i--) {
//
//		}
//
//		for (Integer start : pathKeySet) {
//			char c = s.charAt(start);
//		}
//
//		return null;
//	}
//
//
//
//
//
//
//	// 并查集
//	static class UnionFind {
//
//		//并查集中的结点 树的节点
//		private static class Node{
//			int parent;
//			boolean root;
//
//			private Node(){
//				parent = -1;
//				root = true;
//			}
//
//			public void setParent(int parent) {
//				this.parent = parent;
//			}
//		}
//
//		// 定义集合
//		private Node[] parentArray;
//
//		private Map<Integer, PriorityQueue<Character>> unionSetMap;
//
//		public UnionFind(int len) {
//			//预留长度
//			parentArray = new Node[len + 1];
//			for (int i = 0; i < len; i++) {
//				parentArray[i] = new Node();
//			}
//		}
//
//	}
//
//}
