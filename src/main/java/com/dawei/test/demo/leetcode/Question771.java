package com.dawei.test.demo.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 771. 宝石与石头
 * https://leetcode-cn.com/problems/jewels-and-stones/
 *
 * @author sinbad on 2020/09/13.
 */
public class Question771 {


	public static void main(String[] args) {
		String word = "AAB";
		String wordA = "AAB";
		System.out.println(new Question771().numJewelsInStones(word, wordA));
	}
	public int numJewelsInStones(String J, String S) {
		char[] chars = J.toCharArray();
		Set<Character> stonesSet = new HashSet<>();
		for (char c : chars) {
			stonesSet.add(c);
		}
		int count = 0;
		for (char c : S.toCharArray()) {
			if (stonesSet.contains(c)) {Bea
				count++;
			}
		}
		return count;
	}
}
