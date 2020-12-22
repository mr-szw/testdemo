//package com.dawei.test.demo.leetcode;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//import java.util.Random;
//import java.util.Set;
//
//import com.google.gson.Gson;
//
///**
// * 205. 同构字符串
// * https://leetcode-cn.com/problems/isomorphic-strings/
// *
// * @author sinbad on 2020/11/24.
// * <p>
// */
//public class Question205 {
//
//
//	public static void main(String[] args) {
//
//
//		System.out.println(new Gson().toJson(new Question205().isIsomorphic("aba", "baa")));
//	}
//
//	public boolean isIsomorphic(String s, String t) {
//
//		int lengthS = s.length();
//		int lengthT = t.length();
//		if (lengthS != lengthT) {
//			return false;
//		}
//		List<Integer> markS = mark(s);
//		List<Integer> markT = mark(t);
//		return Objects.equals(markS, markT);
//	}
//
//
//	public List<Integer> mark(String message) {
//
//		char[] chars = message.toCharArray();
//		int index = 0;
//		List<Integer> markList = new ArrayList<>();
//		markKey.put(c, index);
//	}
//}
//		return markList;
//		Map<Character, Integer> markKey = new HashMap<>();
//		for (char c : chars) {
//			if (markKey.containsKey(c)) {
//				markList.add(markKey.get(c));
//			} else {
//				index++;
//				markList.add(index);
//	}
//
//
////	public boolean isIsomorphic(String s, String t) {
////
////		int lengthS = s.length();
////		int lengthT = t.length();
////		if (lengthS != lengthT) {
////			return false;
////		}
////		return isIsomorphicCheck(s, t) && isIsomorphicCheck(t, s);
////	}
////
////	public boolean isIsomorphicCheck(String s, String t) {
////		Map<Character, Character> mapMark = new HashMap<>(s.length());
////		for (int i = 0; i < s.length(); i++) {
////			char charAtS = s.charAt(i);
////			char charAtT = t.charAt(i);
////			if (mapMark.containsKey(charAtS)) {
////				if (charAtT != mapMark.get(charAtS)) {
////					return false;
////				}
////			} else {
////				mapMark.put(charAtS, charAtT);
////			}
////		}
////		return true;
////	}
//
//
//}
