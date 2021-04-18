package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 685. 冗余连接 II
 * https://leetcode-cn.com/problems/redundant-connection-ii/
 * 没完成
 *
 * @author sinbad on 2020/08/31.
 */
public class Question763 {


	public static void main(String[] args) {


		System.out.println(new Question763().partitionLabels("ababcbacadefegdehijhklij"));

	}


	public List<Integer> partitionLabels(String S) {

		//贪心算法
		/**
		 *		a b a b c b a c a d e f e g d e h i j h k l i j
		 *		I				I
		 *		  I        I
		 I	   I
		 * 	第一段范围
		 */


		List<Integer> resultList = new ArrayList<>();
		char[] chars = S.toCharArray();
		int length = chars.length;
		for (int i = 0; i < length; ) {
			Set<Character> hasCheckSet = new HashSet<>();
			//int firstIndexOf = S.indexOf(checkChar);
			int lastIndexOf = 0;
			int num = 0;
			do {
				char checkChar = chars[i];
				if (hasCheckSet.add(checkChar)) {
					int nextLastIndexOf = S.lastIndexOf(checkChar);
					if (nextLastIndexOf > lastIndexOf) {
						lastIndexOf = nextLastIndexOf;
					}
				}
				num++;
				i++;
			} while (i <= lastIndexOf);
			resultList.add(num);
		}
		return resultList;
	}


}
